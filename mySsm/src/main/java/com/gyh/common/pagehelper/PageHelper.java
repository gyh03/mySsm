/*
	The MIT License (MIT)

	Copyright (c) 2014 abel533@gmail.com

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
 */

package com.gyh.common.pagehelper;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.gyh.common.pojo.MessageResult;


/**
 * Mybatis - 通用分页拦截器
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts(@Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }))
public class PageHelper implements Interceptor {
	private static final ThreadLocal LOCAL_PAGE = new ThreadLocal();
	// sql工具类
	private SqlUtil SQLUTIL;
	// RowBounds参数offset作为PageNum使用 - 默认不使用
	private boolean offsetAsPageNum = false;
	// RowBounds是否进行count查询 - 默认不查询
	private boolean rowBoundsWithCount = false;
	// 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页
	private boolean pageSizeZero = false;
	// 分页合理化
	private boolean reasonable = false;

	/**
	 * 开始分页
	 *
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 */
	public synchronized static void startPage(int pageNum, int pageSize) {
		startPage(pageNum, pageSize, true);
	}

	/**
	 * 开始分页
	 *
	 * @param request
	 *            用来提取分页参数,第page页,每页rows条记录
	 * @param useCount
	 *            是否需要计算总页数total
	 */
	public synchronized static void startPage(HttpServletRequest request,
			boolean useCount) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		if (StringUtils.isEmpty(page) || !StringUtils.isNumeric(page)
				|| null == page)
			page = "1";
		if (StringUtils.isEmpty(rows) || !StringUtils.isNumeric(rows)
				|| null == page)
//			rows = SystemConfig.DEFAULT_ROW_SIZE;
			rows ="10";
		startPage(Integer.parseInt(page), Integer.parseInt(rows), useCount);
	}

	/**
	 * 开始分页
	 *
	 * @param request
	 *            用来提取分页参数,第page页,每页rows条记录,默认计算总页数total
	 */
	public synchronized static void startPage(HttpServletRequest request) {
		startPage(request, true);
	}

	// 添加分页总页数
	public synchronized static void addPages(Map<String, Object> result,
			Object page) {
		result.put("page", ((Page) page).getPages());
	}
	
	// 添加分页总页数
	public synchronized static void addPages(MessageResult result,
			Object page) {
		result.setPage(((Page) page).getPages());
	}
	
	// 添加分页总页数
		public synchronized static void addPagesAndTotal(MessageResult result,
				Object page) {
			result.setPage(((Page) page).getPages());
			result.setOther(((Page) page).getTotal());
		}

	/**
	 * 开始分页
	 *
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 * @param count
	 *            是否进行count查询
	 */
	public synchronized static void startPage(int pageNum, int pageSize,
			boolean count) {
		LOCAL_PAGE.remove();
		LOCAL_PAGE.set(new Page(pageNum, pageSize, count));
	}

	/**
	 * 获取分页
	 */
	public synchronized static Page getPage() {
		return (Page) LOCAL_PAGE.get();
	}

	/**
	 * 获取分页参数
	 *
	 * @param rowBounds
	 *            RowBounds参数
	 * @return 返回Page对象
	 */
	private synchronized Page getPage(RowBounds rowBounds) {
		Page page = (Page) LOCAL_PAGE.get();
		// 移除本地变量
		LOCAL_PAGE.remove();
		if (page == null) {
			if (offsetAsPageNum) {
				page = new Page(rowBounds.getOffset(), rowBounds.getLimit(),
						rowBoundsWithCount);
			} else {
				page = new Page(rowBounds, rowBoundsWithCount);
			}
		}
		// 分页合理化
		page.setReasonable(reasonable);
		return page;
	}

	/**
	 * Mybatis拦截器方法
	 *
	 * @param invocation
	 *            拦截器入参
	 * @return 返回执行结果
	 * @throws Throwable
	 *             抛出异常
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] args = invocation.getArgs();
		RowBounds rowBounds = (RowBounds) args[2];
		if (LOCAL_PAGE.get() == null) {// && rowBounds == RowBounds.DEFAULT
			return invocation.proceed();
		} else {
			// 忽略RowBounds-否则会进行Mybatis自带的内存分页
			args[2] = RowBounds.DEFAULT;
			MappedStatement ms = (MappedStatement) args[0];
			Object parameterObject = args[1];
			// 分页信息
			Page page = getPage(rowBounds);
			// pageSizeZero的判断
			if (pageSizeZero && page.getPageSize() == 0) {
				// 执行正常（不分页）查询
				Object result = invocation.proceed();
				// 得到处理结果
				page.addAll((List) result);
				// 相当于查询第一页
				page.setPageNum(1);
				// 这种情况相当于pageSize=total
				page.setPageSize(page.size());
				// 仍然要设置total
				page.setTotal(page.size());
				// 返回结果仍然为Page类型 - 便于后面对接收类型的统一处理
				return page;
			}
			// 简单的通过total的值来判断是否进行count查询
			if (page.isCount()) {
				BoundSql boundSql = null;
				// 只有静态sql需要获取boundSql
				if (!SQLUTIL.isDynamic(ms)) {
					boundSql = ms.getBoundSql(parameterObject);
				}
				// 将参数中的MappedStatement替换为新的qs
				args[0] = SQLUTIL.getCountMappedStatement(ms, boundSql);
				// 查询总数
				Object result = invocation.proceed();
				// 设置总数
				if (((List) result).isEmpty())
					page.setTotal(0);
				else
					page.setTotal((Integer) ((List) result).get(0));
				if (page.getTotal() == 0) {
					return page;
				}
			}
			// 数据不足
			if (page.getStartRow() > page.getTotal()) {
				return page;
			}
			// pageSize>0的时候执行分页查询，pageSize<=0的时候不执行相当于可能只返回了一个count
			if (page.getPageSize() > 0
					&& ((rowBounds == RowBounds.DEFAULT && page.getPageNum() > 0) || rowBounds != RowBounds.DEFAULT)) {
				BoundSql boundSql = null;
				// 只有静态sql需要获取boundSql
				if (!SQLUTIL.isDynamic(ms)) {
					boundSql = ms.getBoundSql(parameterObject);
				}
				// 将参数中的MappedStatement替换为新的qs
				args[0] = SQLUTIL.getPageMappedStatement(ms, boundSql);
				// 动态sql时，boundSql在这儿通过新的ms获取
				if (boundSql == null) {
					boundSql = ((MappedStatement) args[0])
							.getBoundSql(parameterObject);
				}
				// 判断parameterObject，然后赋值
				args[1] = SQLUTIL.setPageParameter(ms, parameterObject,
						boundSql, page);
				// 执行分页查询
				Object result = invocation.proceed();
				// 得到处理结果
				page.addAll((List) result);
			}
			// 返回结果
			return page;
		}
	}

	/**
	 * 只拦截Executor
	 *
	 * @param target
	 * @return
	 */
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	/**
	 * 设置属性值
	 *
	 * @param p
	 *            属性值
	 */
	public void setProperties(Properties p) {
		// 数据库方言
		String dialect = p.getProperty("dialect");
		SQLUTIL = new SqlUtil(dialect);
		// offset作为PageNum使用
		String offsetAsPageNum = p.getProperty("offsetAsPageNum");
		this.offsetAsPageNum = Boolean.parseBoolean(offsetAsPageNum);
		// RowBounds方式是否做count查询
		String rowBoundsWithCount = p.getProperty("rowBoundsWithCount");
		this.rowBoundsWithCount = Boolean.parseBoolean(rowBoundsWithCount);
		// 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页
		String pageSizeZero = p.getProperty("pageSizeZero");
		this.pageSizeZero = Boolean.parseBoolean(pageSizeZero);
		// 分页合理化，true开启，如果分页参数不合理会自动修正。默认false不启用
		String reasonable = p.getProperty("reasonable");
		this.reasonable = Boolean.parseBoolean(reasonable);
	}
}
