package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Baojing;
import model.Chuku;
import model.Diaochu;
import model.Kucun;
import model.Kufang;
import model.Mingxi;
import model.Product;
import model.Quyu;
import model.Ruku;
import model.Rukuliyou;
import model.User;
import model.Vo;

import org.apache.struts2.ServletActionContext;

import util.Pager;
import util.Util;


import com.opensymphony.xwork2.ActionSupport;

import dao.BaojingDao;
import dao.ChukuDao;
import dao.DiaochuDao;
import dao.KucunDao;
import dao.KufangDao;
import dao.MingxiDao;
import dao.ProductDao;
import dao.QuyuDao;
import dao.RukuDao;
import dao.RukuliyouDao;
import dao.UserDao;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
//用户登录操作
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and userlock=0 and role="+role);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			
			List<Baojing> baojings = baojingDao.selectBeanList(0, 9999, " where 1=1 ");
			for(Baojing baojing:baojings){
				baojingDao.deleteBean(baojing);
			}
			
			String s = "";
			if(user.getRole()==0){
			
				
				s = " and user.id= "+user.getId();
			}
			
			List<Kufang> kufangs = kufangDao.selectBeanList(0, 9999, " where kufanglock=0 ");
			for(Kufang kufang:kufangs){
				if(kufang.getRushushuliang()<kufang.getBaojingzuixiaoshuliang()){
					Baojing baojing = new Baojing();
					baojing.setContent(kufang.getName()+"的入库数量小于该库房的最小入库数量。报警！！");
					baojingDao.insertBean(baojing);
				}
				if(kufang.getRushushuliang()>kufang.getBaojingzuidashuliang()){
					Baojing baojing = new Baojing();
					baojing.setContent(kufang.getName()+"的入库数量大于该库房的最大入库数量。报警！！");
					baojingDao.insertBean(baojing);
				}
			}
			
			List<Product> products = productDao.selectBeanList(0, 9999, " where  productlock=0 " +s);
			for(Product product :products){
				if(product.getKucunshuliang()<product.getBaojingxiaxian()){
					Baojing baojing = new Baojing();
					baojing.setContent(product.getUser().getUsername()+"添加的"+product.getName()+"的库存数量小于该商品的最小库存数量。报警！！");
					baojingDao.insertBean(baojing);
				}
				
			}
			
		
		
			session.setAttribute("baojinglist", baojingDao.selectBeanList(0, 9999, " where 1=1 "));

			
			this.setUrl("main.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='index.jsp';</script>");
		}
		return null;
	}
//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("index.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");
		User bean = userDao.selectBean(" where username= '" + u.getUsername()
				+ "' and password= '" + password1 + "'");
		if (!password2.equals(password3)) {
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('两次输入密码不一致');window.location.href='method!changepwd';</script>");
		} else if (bean != null) {
			bean.setPassword(password2);
			userDao.updateBean(bean);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作成功');window.location.href='method!changepwd';</script>");
		} else {
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd';</script>");
		}
	}
	
	
	//用户信息页面
	public String user() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User bean = userDao.selectBean(" where id= " + user.getId());
		request.setAttribute("bean", bean);
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}

	
	private KufangDao kufangDao;

	public KufangDao getKufangDao() {
		return kufangDao;
	}

	public void setKufangDao(KufangDao kufangDao) {
		this.kufangDao = kufangDao;
	}
	
	
	
	//库房列表
	public String kufanglist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String kufangbianhao = request.getParameter("kufangbianhao");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (name != null && !"".equals(name)) {

			sb.append("name like '%" + name + "%'");
			sb.append(" and ");
			request.setAttribute("name", name);
		}

		if (kufangbianhao != null && !"".equals(kufangbianhao)) {
			sb.append("kufangbianhao like '%" + kufangbianhao + "%'");
			sb.append(" and ");
			request.setAttribute("kufangbianhao", kufangbianhao);
		}
		
		

		sb.append(" kufanglock=0  order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kufangDao.selectBeanCount(" where kufanglock=0 ");
		request.setAttribute("list", kufangDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kufanglist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!kufanglist");
		request.setAttribute("url2", "method!kufang");
		this.setUrl("kufang/kufanglist.jsp");
		return SUCCESS;

	}
//跳转到添加库房页面
	public String kufangadd() {
		this.setUrl("kufang/kufangadd.jsp");
		return SUCCESS;
	}
//添加库房操作
	public void kufangadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String mianji = request.getParameter("mianji");
		String zuidashuliang = request.getParameter("zuidashuliang");
		String name = request.getParameter("name");
		String baojingzuixiaoshuliang = request.getParameter("baojingzuixiaoshuliang");
		String baojingzuidashuliang = request.getParameter("baojingzuidashuliang");
		Kufang bean = new Kufang();
		bean.setBeizhu(beizhu);
		bean.setCreatetime(new Date());
		bean.setKufangbianhao(Util.getTime());
		bean.setMianji(mianji);
		bean.setName(name);
		bean.setZuidashuliang(Integer.parseInt(zuidashuliang));
		bean.setBaojingzuixiaoshuliang(Integer.parseInt(baojingzuixiaoshuliang));
		bean.setBaojingzuidashuliang(Integer.parseInt(baojingzuidashuliang));
		kufangDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!kufanglist';</script>");
		

	}
//跳转到更新库房页面
	public String kufangupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kufang bean = kufangDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("kufang/kufangupdate.jsp");
		return SUCCESS;
	}
//更新库房操作
	public void kufangupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String mianji = request.getParameter("mianji");
		String zuidashuliang = request.getParameter("zuidashuliang");
		String name = request.getParameter("name");
		String baojingzuixiaoshuliang = request.getParameter("baojingzuixiaoshuliang");
		String baojingzuidashuliang = request.getParameter("baojingzuidashuliang");
		Kufang bean = kufangDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setBeizhu(beizhu);
		bean.setMianji(mianji);
		bean.setName(name);
		bean.setZuidashuliang(Integer.parseInt(zuidashuliang));
		bean.setBaojingzuixiaoshuliang(Integer.parseInt(baojingzuixiaoshuliang));
		bean.setBaojingzuidashuliang(Integer.parseInt(baojingzuidashuliang));
		kufangDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!kufanglist';</script>");
	}
//删除库房操作
	public void kufangdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kufang bean = kufangDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setKufanglock(1);
		kufangDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!kufanglist';</script>");
	}
	
	//跳转到查看库房页面
	public String kufangupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kufang bean = kufangDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("kufang/kufangupdate3.jsp");
		return SUCCESS;
	}
	
	
	private RukuliyouDao rukuliyouDao;

	public RukuliyouDao getRukuliyouDao() {
		return rukuliyouDao;
	}

	public void setRukuliyouDao(RukuliyouDao rukuliyouDao) {
		this.rukuliyouDao = rukuliyouDao;
	}
	
	
	//入库理由列表
	public String rukuliyoulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String liyou = request.getParameter("liyou");
		String infotype = request.getParameter("infotype");
	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (liyou != null && !"".equals(liyou)) {

			sb.append("liyou like '%" + liyou + "%'");
			sb.append(" and ");
			request.setAttribute("liyou", liyou);
		}
		
		if (infotype != null && !"".equals(infotype)) {

			sb.append("infotype like '%" + infotype + "%'");
			sb.append(" and ");
			request.setAttribute("infotype", infotype);
		}

		
		

		sb.append(" rukuliyoulock=0  order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = rukuliyouDao.selectBeanCount(" where rukuliyoulock=0 ");
		request.setAttribute("list", rukuliyouDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!rukuliyoulist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!rukuliyoulist");
		request.setAttribute("url2", "method!rukuliyou");
		this.setUrl("rukuliyou/rukuliyoulist.jsp");
		return SUCCESS;

	}
//跳转到添加入库理由页面
	public String rukuliyouadd() {
		this.setUrl("rukuliyou/rukuliyouadd.jsp");
		return SUCCESS;
	}
//添加入库理由操作
	public void rukuliyouadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String liyou = request.getParameter("liyou");
		String infotype = request.getParameter("infotype");
	
		Rukuliyou bean = new Rukuliyou();
		bean.setCreatetime(new Date());
		bean.setLiyou(liyou);
		bean.setInfotype(infotype);
		
		rukuliyouDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukuliyoulist';</script>");
		

	}
//跳转到更新入库理由页面
	public String rukuliyouupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Rukuliyou bean = rukuliyouDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("rukuliyou/rukuliyouupdate.jsp");
		return SUCCESS;
	}
//更新入库理由操作
	public void rukuliyouupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String liyou = request.getParameter("liyou");
		String infotype = request.getParameter("infotype");

		Rukuliyou bean = rukuliyouDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setLiyou(liyou);
		bean.setInfotype(infotype);
		
		rukuliyouDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukuliyoulist';</script>");
	}
//删除入库理由操作
	public void rukuliyoudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Rukuliyou bean = rukuliyouDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setRukuliyoulock(1);
		rukuliyouDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukuliyoulist';</script>");
	}
	
	//跳转到查看入库理由页面
	public String rukuliyouupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Rukuliyou bean = rukuliyouDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("rukuliyou/rukuliyouupdate3.jsp");
		return SUCCESS;
	}
	
	
	//仓库管理员列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");
	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		

		sb.append(" userlock=0  and role=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(" where userlock=0 ");
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}
//跳转到添加仓库管理员页面
	public String useradd() {
		this.setUrl("user/useradd.jsp");
		return SUCCESS;
	}
//添加仓库管理员操作
	public void useradd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");
		String username = request.getParameter("username");
	
		User bean = userDao.selectBean(" where username='"+username+"' and userlock=0 ");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(new Date());
			bean.setPassword("111111");
			bean.setTruename(truename);
			bean.setUsername(username);
			userDao.insertBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('该用户名已经存在，注册失败');window.location.href='method!userlist';</script>");
		}
		
		

	}
//跳转到更新仓库管理员页面
	public String userupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate.jsp");
		return SUCCESS;
	}
//更新仓库管理员操作
	public void userupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");


		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTruename(truename);
		
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
//删除仓库管理员操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setUserlock(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	//跳转到查看仓库管理员页面
	public String userupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate3.jsp");
		return SUCCESS;
	}
	
	
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	//产品列表
	public String productlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");

	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (name != null && !"".equals(name)) {

			sb.append("name like '%" + name + "%'");
			sb.append(" and ");
			request.setAttribute("name", name);
		}
		

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" productlock=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = productDao.selectBeanCount(" where productlock=0 ");
		request.setAttribute("list", productDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!productlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!productlist");
		request.setAttribute("url2", "method!product");
		this.setUrl("product/productlist.jsp");
		return SUCCESS;

	}
//跳转到添加产品页面
	public String productadd() {
		this.setUrl("product/productadd.jsp");
		return SUCCESS;
	}
	
	//添加产品操作
	public void productadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String baojingxiaxian = request.getParameter("baojingxiaxian");
		String gongyingshang = request.getParameter("gongyingshang");
		String name = request.getParameter("name");
		String shengchandizhi = request.getParameter("shengchandizhi");
	
	
		Product bean =  new Product();
		bean.setBaojingxiaxian(Integer.parseInt(baojingxiaxian));
		bean.setCreatetime(new Date());
		bean.setGongyingshang(gongyingshang);
		bean.setName(name);
		bean.setShengchandizhi(shengchandizhi);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setUser(user);
		productDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!productlist';</script>");
		
		

	}
//跳转到更新产品页面
	public String productupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("product/productupdate.jsp");
		return SUCCESS;
	}
//更新产品操作
	public void productupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String baojingxiaxian = request.getParameter("baojingxiaxian");
		String gongyingshang = request.getParameter("gongyingshang");
		String name = request.getParameter("name");
		String shengchandizhi = request.getParameter("shengchandizhi");


		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setBaojingxiaxian(Integer.parseInt(baojingxiaxian));
		bean.setGongyingshang(gongyingshang);
		bean.setName(name);
		bean.setShengchandizhi(shengchandizhi);
		
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!productlist';</script>");
	}
//删除产品操作
	public void productdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setProductlock(1);
		productDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!productlist';</script>");
	}
	
	//跳转到查看产品页面
	public String productupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Product bean = productDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("product/productupdate3.jsp");
		return SUCCESS;
	}

	private RukuDao rukuDao;

	public RukuDao getRukuDao() {
		return rukuDao;
	}

	public void setRukuDao(RukuDao rukuDao) {
		this.rukuDao = rukuDao;
	}
	
	
	//入库单列表
	public String rukulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String quyu = request.getParameter("quyu");

	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}
		
		
		

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" rukulock=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = rukuDao.selectBeanCount(" where rukulock=0 ");
		request.setAttribute("list", rukuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!rukulist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!rukulist");
		request.setAttribute("url2", "method!ruku");
		this.setUrl("ruku/rukulist.jsp");
		return SUCCESS;

	}
	//跳转到添加入库单页面
	public String rukuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 "));
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='入库' "));		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		request.setAttribute("productlist", productDao.selectBeanList(0, 9999, " where productlock=0 and user.id= "+user.getId()));
		this.setUrl("ruku/rukuadd.jsp");
		return SUCCESS;
	}
	
	//添加入库单操作
	public void rukuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String quyu = request.getParameter("quyu");
		String product = request.getParameter("product");
		String rukushuliang = request.getParameter("rukushuliang");
		String rukuliyou = request.getParameter("rukuliyou");
		Kufang k = kufangDao.selectBean(" where id= "+kufang);
		if(Integer.parseInt(rukushuliang)> (k.getZuidashuliang()-k.getRushushuliang())){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('入库数量大于库房剩余入库数量，操作失败');window.location.href='method!rukuadd';</script>");
			return;
		}

		Product p = productDao.selectBean(" where id= "+product);
		Ruku bean =  new Ruku();
		bean.setQuyu(quyuDao.selectBean(" where id= "+quyu));
		bean.setRukuliyou(rukuliyouDao.selectBean(" where id= "+rukuliyou));
		bean.setCreatetime(new Date());
		bean.setKufang(k);
		bean.setProduct(p);
		bean.setRukudanbianhao(Util.getTime());
		bean.setRukushuliang(Integer.parseInt(rukushuliang));
		bean.setShenhezhuangtai("未审核");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setUser(user);
		rukuDao.insertBean(bean);
		
		//库房锁定
		k.setRushushuliang(k.getRushushuliang()+Integer.parseInt(rukushuliang));
		kufangDao.updateBean(k);
		
		
		
		if((Integer.parseInt(rukushuliang)+p.getKucunshuliang())<p.getBaojingxiaxian()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('警告：该产品的入库数量和库存数量之和小于该产品的缺货报警数量');window.location.href='method!rukulist';</script>");
			return;
		}
		
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukulist';</script>");
		
		

	}
//跳转到更新入库单页面
	public String rukuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 "));
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='入库' "));		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		request.setAttribute("productlist", productDao.selectBeanList(0, 9999, " where productlock=0 and user.id= "+user.getId()));
		
		this.setUrl("ruku/rukuupdate.jsp");
		return SUCCESS;
	}
//更新入库单操作
	public void rukuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String product = request.getParameter("product");
		String rukushuliang = request.getParameter("rukushuliang");
		String rukuliyou = request.getParameter("rukuliyou");
		
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		String kufang = request.getParameter("kufang");
		String quyu = request.getParameter("quyu");
		if("0".equals(kufang)){
			kufang = bean.getKufang().getId()+"";
			quyu = bean.getQuyu().getId()+"";
		}
		
		Kufang k = kufangDao.selectBean(" where id= "+kufang);
		
		
		
		if(Integer.parseInt(rukushuliang)> (k.getZuidashuliang()-k.getRushushuliang())){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('入库数量大于库房剩余入库数量，操作失败');window.location.href='method!rukuupdate?id="+bean.getId()+"';</script>");
			return;
		}
		
		
		//重新锁定入库商品数量
		k.setRushushuliang(k.getRushushuliang()+Integer.parseInt(rukushuliang));
		kufangDao.updateBean(k);

		
		bean.setRukuliyou(rukuliyouDao.selectBean(" where id= "+rukuliyou));
		bean.setCreatetime(new Date());
		bean.setKufang(k);
		bean.setProduct(productDao.selectBean(" where id= "+product));
		bean.setRukushuliang(Integer.parseInt(rukushuliang));
		bean.setShenhezhuangtai("未审核");
		bean.setQuyu(quyuDao.selectBean(" where id= "+quyu));
		rukuDao.updateBean(bean);

		Product p = productDao.selectBean(" where id= "+product);
		
		
		
		if((Integer.parseInt(rukushuliang)+p.getKucunshuliang())<p.getBaojingxiaxian()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('警告：该产品的入库数量和库存数量之和小于该产品的缺货报警数量');window.location.href='method!rukulist';</script>");
			return;
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukulist';</script>");
	}
//删除入库单操作
	public void rukudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setRukulock(1);
		rukuDao.updateBean(bean);
		//解除锁定
		Kufang kufang = kufangDao.selectBean(" where id= "+bean.getKufang().getId());
		kufang.setRushushuliang(kufang.getRushushuliang()-bean.getRukushuliang());
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukulist';</script>");
	}
	
	//跳转到查看入库单页面
	public String rukuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("ruku/rukuupdate3.jsp");
		return SUCCESS;
	}
	
	
	//入库单审核
	public String rukulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<Kufang> list = kufangDao.selectBeanList(0, 9999, " where  kufanglock=0");
		for(Kufang k:list){
			int count = 0;
			List<Kucun> list2 = kucunDao.selectBeanList(0, 9999, "  where kufang.id="+k.getId());
			for(Kucun kucun:list2){
				count +=kucun.getShuliang();
			}
			k.setShijirukushuliang(count);
			kufangDao.updateBean(k);
			
		}
		
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String quyu = request.getParameter("quyu");

	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}
		
		
		

		sb.append(" rukulock=0   order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = rukuDao.selectBeanCount(" where rukulock=0 ");
		request.setAttribute("list", rukuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!rukulist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!rukulist2");
		request.setAttribute("url2", "method!ruku");
		this.setUrl("ruku/rukulist2.jsp");
		return SUCCESS;

	}
	
	
	//跳转到审核入库单页面
	public String rukuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 "));
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='入库' "));		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		request.setAttribute("productlist", productDao.selectBeanList(0, 9999, " where productlock=0 and user.id= "+user.getId()));
		
		this.setUrl("ruku/rukuupdate5.jsp");
		return SUCCESS;
	}
	
	
	//审核入库单操作
	public void rukuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String shenhefanhui = request.getParameter("shenhefanhui");
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		Kufang kufang = kufangDao.selectBean(" where id= "+bean.getKufang().getId());
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		bean.setShenheuser(user);
		
		if("审核未通过".equals(shenhezhuangtai)){
			kufang.setRushushuliang(kufang.getRushushuliang()-bean.getRukushuliang());
			kufangDao.updateBean(kufang);
			
		}
		
		if("审核通过".equals(shenhezhuangtai)){
			
			Product product = productDao.selectBean(" where id= "+bean.getProduct().getId());
			product.setKucunshuliang(product.getKucunshuliang()+bean.getRukushuliang());
			productDao.updateBean(product);
		}
		
		bean.setShenhefanhui(shenhefanhui);
		bean.setShenhezhuangtai(shenhezhuangtai);
		rukuDao.updateBean(bean);		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukulist2';</script>");

	}
	
	private KucunDao kucunDao;

	public KucunDao getKucunDao() {
		return kucunDao;
	}

	public void setKucunDao(KucunDao kucunDao) {
		this.kucunDao = kucunDao;
	}
	
	
	
	//确认入库操作
	public void rukudelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Ruku bean = rukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setShenhezhuangtai("成功入库");
		rukuDao.updateBean(bean);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Kucun kucun = kucunDao.selectBean(" where kufang.id= "+bean.getKufang().getId() +" and product.id= "
				+bean.getProduct().getId() +" and user.id="+user.getId()+" and quyu.id= "+bean.getQuyu().getId());
		if(kucun==null){
			kucun = new Kucun();
			kucun.setKufang(bean.getKufang());
			kucun.setProduct(bean.getProduct());
			kucun.setShuliang(bean.getRukushuliang());
			kucun.setUser(user);
			kucun.setQuyu(bean.getQuyu());
			kucunDao.insertBean(kucun);
			
			this.addMingxi(bean.getProduct().getName(), "产品入库", bean.getRukuliyou().getLiyou(),
					bean.getRukushuliang(), user,"产品入库到"+bean.getKufang().getName()+"，区域为"+bean.getQuyu().getQuyuming(),
					bean.getShenheuser().getUsername(),bean.getProduct().getGongyingshang(),null);

			
		}else{
			kucun.setShuliang(kucun.getShuliang()+bean.getRukushuliang());
			kucunDao.updateBean(kucun);
			this.addMingxi(bean.getProduct().getName(), "产品入库", bean.getRukuliyou().getLiyou(),
					bean.getRukushuliang(), user,"产品入库到"+bean.getKufang().getName()+"，区域为"+bean.getQuyu().getQuyuming(),
					bean.getShenheuser().getUsername(),bean.getProduct().getGongyingshang(),null);
		}
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!rukulist';</script>");
	}
	
	
	
	//入库查询
	public String rukulist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		String quyu = request.getParameter("quyu");

	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		if (time1 != null && !"".equals(time1)) {

			sb.append(" createtime > '" + time1 + "'");
			sb.append(" and ");
			request.setAttribute("time1", time1);
		}
		
		if (time2 != null && !"".equals(time2)) {

			sb.append(" createtime <= '" + time2 + "'");
			sb.append(" and ");
			request.setAttribute("time2", time2);
		}
		
		
		

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" rukulock=0 and shenhezhuangtai='成功入库'  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = rukuDao.selectBeanCount(" where rukulock=0 ");
		request.setAttribute("list", rukuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!rukulist3", "共有" + total + "条记录"));
		request.setAttribute("url", "method!rukulist3");
		request.setAttribute("url2", "method!ruku");
		this.setUrl("ruku/rukulist3.jsp");
		return SUCCESS;

	}
	
	
	
	
	private ChukuDao chukuDao;

	public ChukuDao getChukuDao() {
		return chukuDao;
	}

	public void setChukuDao(ChukuDao chukuDao) {
		this.chukuDao = chukuDao;
	}
	
	
	//出库登记
	public String chukulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String quyu = request.getParameter("quyu");
		
		List<Kucun> list = kucunDao.selectBeanList(0, 9999, " where 1=1 ");
		for(Kucun c:list){
			int count = 0;
			
			List<Chuku>  list2 = chukuDao.selectBeanList(0, 9999, " where chukulock=0 and shenhezhuangtai='未审核' and kufang.id= "+c.getKufang().getId()
					+" and quyu.id= "+c.getQuyu().getId()+" and product.id= "+c.getProduct().getId());
			for(Chuku chuku:list2){
				count+=chuku.getChukushuliang();
			}
			
			
			List<Diaochu>  list3 = diaochuDao.selectBeanList(0, 9999, " where diaochulock=0 and shenhezhuangtai='未审核' and  kufang1.id=  "+c.getKufang().getId() 
					+" and quyu1.id="+c.getQuyu().getId()+" and product.id= "+c.getProduct().getId());
			for(Diaochu diaochu:list3){
				count+=diaochu.getDiaodushuliang();
			}
			
			c.setKeshengqingkecun(c.getShuliang()-count);
			kucunDao.updateBean(c);
			
		}
		
		

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kucunDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", kucunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kucunlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!chukulist");
		request.setAttribute("url2", "method!chuku");
		this.setUrl("kucun/kucunlist2.jsp");
		return SUCCESS;

	}
	
	
	//跳转到添加出库单页面
	public String chukuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kucun kucun = kucunDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", kucun);
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='出库' "));		
		this.setUrl("chuku/chukuadd.jsp");
		return SUCCESS;
	}
	
	//添加出库单操作
	public void chukuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kucun kucun = kucunDao.selectBean(" where id= "+request.getParameter("kucunid"));

		String chukushuliang = request.getParameter("chukushuliang");
		String liyou = request.getParameter("liyou");
		String jingxiaoshang = request.getParameter("jingxiaoshang");
		String lianxidianhua = request.getParameter("lianxidianhua");
		
		
		if(Integer.parseInt(chukushuliang)> kucun.getKeshengqingkecun()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('出库数量大于最大可申请出库数量，操作失败');window.location.href='method!chukuadd?id="+kucun.getId()+"';</script>");
			return;
		}
		
		if(Integer.parseInt(chukushuliang)> kucun.getShuliang()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('出库数量大于库存数量，操作失败');window.location.href='method!chukuadd?id="+kucun.getId()+"';</script>");
			return;
		}
	
		Chuku bean =  new Chuku();
		bean.setLianxidianhua(lianxidianhua);
		bean.setJingxiaoshang(jingxiaoshang);
		bean.setQuyu(quyuDao.selectBean(" where id= "+kucun.getQuyu().getId()));
		bean.setKucun(kucun);
		bean.setBianhao(Util.getTime());
		bean.setChukushuliang(Integer.parseInt(chukushuliang));
		bean.setCreatetime(new Date());
		bean.setKufang(kufangDao.selectBean(" where id= "+kucun.getKufang().getId()));
		bean.setLiyou(rukuliyouDao.selectBean(" where id= "+liyou));
		bean.setProduct(productDao.selectBean(" where id= "+kucun.getProduct().getId()));
		bean.setShenhezhuangtai("未审核");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setUser(user);
		chukuDao.insertBean(bean);
		
		Product p = kucun.getProduct();
		
		if((p.getKucunshuliang()-Integer.parseInt(chukushuliang))<p.getBaojingxiaxian()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('警告：该产品的库存数量小于该产品的缺货报警数量');window.location.href='method!chukulist';</script>");
			return;
		}
		
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!chukulist';</script>");

	}
	
	//出库查询
	public String chukulist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String quyu = request.getParameter("quyu");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}
		
		
		

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append(" chukulock=0  and user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = chukuDao.selectBeanCount(" where chukulock=0 ");
		request.setAttribute("list", chukuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!chukulist3", "共有" + total + "条记录"));
		request.setAttribute("url", "method!chukulist3");
		request.setAttribute("url2", "method!chuku");
		this.setUrl("chuku/chukulist3.jsp");
		return SUCCESS;

	}
	
	//跳转到更新出库单页面
	public String chukuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);		
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='出库' "));	
		this.setUrl("chuku/chukuupdate.jsp");
		return SUCCESS;
	}
//更新出库单操作
	public void chukuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chukushuliang = request.getParameter("chukushuliang");
		String jingxiaoshang = request.getParameter("jingxiaoshang");
		String lianxidianhua = request.getParameter("lianxidianhua");
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		Kucun kucun = kucunDao.selectBean(" where id= "+bean.getKucun().getId());
		String liyou = request.getParameter("liyou");
		
		if(Integer.parseInt(chukushuliang)> kucun.getKeshengqingkecun()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('出库数量大于最大可申请出库数量，操作失败');window.location.href='method!chukuadd?id="+kucun.getId()+"';</script>");
			return;
		}
		
		if(Integer.parseInt(chukushuliang)> kucun.getShuliang()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('出库数量大于库存数量，操作失败');window.location.href='method!chukuupdate?id="+bean.getId()+"';</script>");
			return;
		}

		bean.setJingxiaoshang(jingxiaoshang);
		bean.setLianxidianhua(lianxidianhua);
		bean.setChukushuliang(Integer.parseInt(chukushuliang));
		bean.setCreatetime(new Date());

		bean.setLiyou(rukuliyouDao.selectBean(" where id= "+liyou));

		bean.setShenhezhuangtai("未审核");

		
		chukuDao.updateBean(bean);
		
		
		Product p = kucun.getProduct();
		
		if((p.getKucunshuliang()-Integer.parseInt(chukushuliang))<p.getBaojingxiaxian()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('警告：该产品的库存数量小于该产品的缺货报警数量');window.location.href='method!chukulist';</script>");
			return;
		}
		
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!chukulist3';</script>");
	}
//删除出库单操作
	public void chukudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setChukulock(1);
		chukuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!chukulist3';</script>");
	}
	
	//跳转到查看出库单页面
	public String chukuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("chuku/chukuupdate3.jsp");
		return SUCCESS;
	}
	
	
	//审核出库单
	public String chukulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String quyu = request.getParameter("quyu");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}
		sb.append(" chukulock=0   order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = chukuDao.selectBeanCount(" where chukulock=0 ");
		request.setAttribute("list", chukuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!chukulist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!chukulist2");
		request.setAttribute("url2", "method!chuku");
		this.setUrl("chuku/chukulist2.jsp");
		return SUCCESS;

	}
	
	
	//跳转到审核出库单页面
	public String chukuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		this.setUrl("chuku/chukuupdate5.jsp");
		return SUCCESS;
	}
	
	
	//审核出库单操作
	public void chukuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String shenhefanhui = request.getParameter("shenhefanhui");
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		bean.setShenheuser(user);
		
		Kucun kucun = kucunDao.selectBean(" where id= "+bean.getKucun().getId());

		if(bean.getChukushuliang()> kucun.getShuliang()){
			bean.setShenhefanhui("出库数量大于库存数量");
			bean.setShenhezhuangtai("审核未通过");
			chukuDao.updateBean(bean);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('出库数量大于库存数量，审核未通过');window.location.href='method!chukulist2';</script>");
			return;
		}
		
		if("审核通过".equals(shenhezhuangtai)){
			Kufang kufang = kufangDao.selectBean(" where id= "+bean.getKufang().getId());
			
			kufang.setRushushuliang(kufang.getRushushuliang()-bean.getChukushuliang());
			kufangDao.updateBean(kufang);
			
			kucun.setShuliang(kucun.getShuliang()-bean.getChukushuliang());
			kucunDao.updateBean(kucun);
			
			
			Product product = productDao.selectBean(" where id= "+bean.getProduct().getId());
			product.setKucunshuliang(product.getKucunshuliang()-bean.getChukushuliang());
			productDao.updateBean(product);
			
		}
		bean.setShenhefanhui(shenhefanhui);
		bean.setShenhezhuangtai(shenhezhuangtai);
		chukuDao.updateBean(bean);

		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!chukulist2';</script>");

	}
	
	
	//确认出库操作
	public void chukudelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Chuku bean = chukuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setShenhezhuangtai("成功出库");
		chukuDao.updateBean(bean);
		
		
		this.addMingxi(bean.getProduct().getName(), "产品出库", bean.getLiyou().getLiyou(),
				bean.getChukushuliang(),bean.getUser(),"产品从"+bean.getKufang().getName()+"仓库，"+bean.getQuyu().getQuyuming()+"区域出库",
				bean.getShenheuser().getUsername(),bean.getProduct().getGongyingshang(),bean.getJingxiaoshang());
		

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!chukulist3';</script>");
	}
	
	
	
	private DiaochuDao diaochuDao;
	
	
	public DiaochuDao getDiaochuDao() {
		return diaochuDao;
	}

	public void setDiaochuDao(DiaochuDao diaochuDao) {
		this.diaochuDao = diaochuDao;
	}

	//移库申请登记
	public String diaochulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String quyu = request.getParameter("quyu");
		
		
		List<Kucun> list = kucunDao.selectBeanList(0, 9999, " where 1=1 ");
		for(Kucun c:list){
			int count = 0;
			
			List<Chuku>  list2 = chukuDao.selectBeanList(0, 9999, " where chukulock=0 and shenhezhuangtai='未审核' and kufang.id= "+c.getKufang().getId()
					+" and quyu.id= "+c.getQuyu().getId()+" and product.id= "+c.getProduct().getId());
			for(Chuku chuku:list2){
				count+=chuku.getChukushuliang();
			}
			
			
			List<Diaochu>  list3 = diaochuDao.selectBeanList(0, 9999, " where diaochulock=0 and shenhezhuangtai='未审核' and  kufang1.id=  "+c.getKufang().getId() 
					+" and quyu1.id="+c.getQuyu().getId()+" and product.id= "+c.getProduct().getId());
			for(Diaochu diaochu:list3){
				count+=diaochu.getDiaodushuliang();
			}
			
			c.setKeshengqingkecun(c.getShuliang()-count);
			kucunDao.updateBean(c);
			
		}


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kucunDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", kucunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kucunlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!diaochulist");
		request.setAttribute("url2", "method!diaochu");
		this.setUrl("kucun/kucunlist3.jsp");
		return SUCCESS;

	}
	
	
	
	//跳转到添加调出单页面
	public String diaochuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kucun kucun = kucunDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", kucun);
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 and id!= "+kucun.getKufang().getId()));
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='调度' "));		
		this.setUrl("diaochu/diaochuadd.jsp");
		return SUCCESS;
	}
	
	//添加调出单操作
	public void diaochuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang1 = request.getParameter("kufang1");//调出库房
		String kufang2 = request.getParameter("kufang2");//调入库房
		String quyu1 = request.getParameter("quyu1");//
		String quyu2 = request.getParameter("quyu2");//
		
		String product = request.getParameter("product");
		String diaodushuliang = request.getParameter("diaodushuliang");
		String rukuliyou = request.getParameter("rukuliyou");
		Kufang k = kufangDao.selectBean(" where id= "+kufang2);//调入库房
		
		String kucunid = request.getParameter("kucunid");
		Kucun kucun = kucunDao.selectBean(" where id= "+kucunid);
		if(Integer.parseInt(diaodushuliang)> kucun.getKeshengqingkecun()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('调度数量大于最大可申请调度数量，操作失败');window.location.href='method!diaochulist';</script>");
			return;
		}
		
		
		if(Integer.parseInt(diaodushuliang)> (k.getZuidashuliang()-k.getRushushuliang())){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('调度数量大于调入库房剩余调度数量，操作失败');window.location.href='method!diaochulist';</script>");
			return;
		}
	
		Diaochu bean =  new Diaochu();
		bean.setQuyu2(quyuDao.selectBean(" where id= "+quyu2));
		bean.setQuyu1(quyuDao.selectBean(" where id= "+quyu1));
		bean.setCreatetime(new Date());
		bean.setDiaodushuliang(Integer.parseInt(diaodushuliang));
		bean.setKufang1(kufangDao.selectBean(" where id= "+kufang1));
		bean.setKufang2(k);
		bean.setProduct(productDao.selectBean(" where id= "+product));
		bean.setRukudanbianhao(Util.getTime());
		bean.setRukuliyou(rukuliyouDao.selectBean(" where id= "+rukuliyou));
		bean.setShenhezhuangtai("未审核");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setUser(user);
		diaochuDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!diaochulist';</script>");
		
		

	}
	
	
	//移库查询
	public String diaochulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang1 = request.getParameter("kufang1");
		String product = request.getParameter("product");
		String kufang2 = request.getParameter("kufang2");
		String quyu1 = request.getParameter("quyu1");
		String quyu2 = request.getParameter("quyu2");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang1 != null && !"".equals(kufang1)) {

			sb.append(" kufang1.name like '%" + kufang1 + "%'");
			sb.append(" and ");
			request.setAttribute("kufang1", kufang1);
		}
		
		if (quyu1 != null && !"".equals(quyu1)) {

			sb.append(" quyu1.quyuming like '%" + quyu1 + "%'");
			sb.append(" and ");
			request.setAttribute("quyu1", quyu1);
		}
		
		if (quyu2 != null && !"".equals(quyu2)) {

			sb.append(" quyu2.quyuming like '%" + quyu2 + "%'");
			sb.append(" and ");
			request.setAttribute("quyu2", quyu2);
		}
		
		if (kufang2 != null && !"".equals(kufang2)) {

			sb.append(" kufang2.name like '%" + kufang2 + "%'");
			sb.append(" and ");
			request.setAttribute("kufang2", kufang2);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+"  and diaochulock=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = diaochuDao.selectBeanCount(" where diaochulock=0 ");
		request.setAttribute("list", diaochuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!diaochulist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!diaochulist2");
		request.setAttribute("url2", "method!diaochu");
		this.setUrl("diaochu/diaochulist2.jsp");
		return SUCCESS;

	}
	
	
	//跳转到更新调出单页面
	public String diaochuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Kucun kucun = kucunDao.selectBean(" where kufang.id= "+bean.getKufang1().getId()+" and quyu.id= "+bean.getQuyu1().getId()+
				" and product.id= "+bean.getProduct().getId()+" and user.id= "+user.getId());
		request.setAttribute("kucun", kucun);
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 and id!= "+bean.getKufang1().getId()));
		request.setAttribute("rukuliyoulist", rukuliyouDao.selectBeanList(0, 9999, " where rukuliyoulock=0 and infotype='调度' "));			
		this.setUrl("diaochu/diaochuupdate.jsp");
		return SUCCESS;
	}
	
	
	//更新调出单操作
	public void diaochuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		String kufang2 = request.getParameter("kufang2");
		String quyu2 = request.getParameter("quyu2");
		if("0".equals(kufang2)){
			kufang2 = bean.getKufang2().getId()+"";
			quyu2 = bean.getQuyu2().getId()+"";
		}
		
		String diaodushuliang = request.getParameter("diaodushuliang");
		String rukuliyou = request.getParameter("rukuliyou");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Kucun kucun = kucunDao.selectBean(" where kufang.id= "+bean.getKufang1().getId()+" and quyu.id= "+bean.getQuyu1().getId()+
				" and product.id= "+bean.getProduct().getId()+" and user.id= "+user.getId());
		if(Integer.parseInt(diaodushuliang)> kucun.getKeshengqingkecun()){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('调度数量大于最大可申请调度数量，操作失败');window.location.href='method!diaochulist';</script>");
			return;
		}
		
		
		Kufang k = kufangDao.selectBean(" where id= "+bean.getKufang2().getId());
		
		if(Integer.parseInt(diaodushuliang)> (k.getZuidashuliang()-k.getRushushuliang())){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('调度数量大于调入库房剩余调度数量，操作失败');window.location.href='method!diaochuupdate?id="+bean.getId()+"';</script>");
			return;
		}
		bean.setQuyu2(quyuDao.selectBean(" where id= "+quyu2));
		bean.setCreatetime(new Date());
		bean.setDiaodushuliang(Integer.parseInt(diaodushuliang));
		bean.setKufang2(kufangDao.selectBean(" where id= "+kufang2));
		bean.setRukuliyou(rukuliyouDao.selectBean(" where id= "+rukuliyou));
		bean.setShenhezhuangtai("未审核");
		diaochuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!diaochulist2';</script>");
	}
	
	

	//删除调出单操作
	public void diaochudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDiaochulock(1);
		diaochuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!diaochulist2';</script>");
	}
	
	//跳转到查看调出单页面
	public String diaochuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("diaochu/diaochuupdate3.jsp");
		return SUCCESS;
	}
	
	
	//移库单审核
	public String diaochulist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufang1 = request.getParameter("kufang1");
		String product = request.getParameter("product");
		String kufang2 = request.getParameter("kufang2");
		String quyu1 = request.getParameter("quyu1");
		String quyu2 = request.getParameter("quyu2");
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang1 != null && !"".equals(kufang1)) {

			sb.append(" kufang1.name like '%" + kufang1 + "%'");
			sb.append(" and ");
			request.setAttribute("kufang1", kufang1);
		}
		
		if (kufang2 != null && !"".equals(kufang2)) {

			sb.append(" kufang2.name like '%" + kufang2 + "%'");
			sb.append(" and ");
			request.setAttribute("kufang2", kufang2);
		}
		
		if (quyu1 != null && !"".equals(quyu1)) {

			sb.append(" quyu1.quyuming like '%" + quyu1 + "%'");
			sb.append(" and ");
			request.setAttribute("quyu1", quyu1);
		}
		
		if (quyu2 != null && !"".equals(quyu2)) {

			sb.append(" quyu2.quyuming like '%" + quyu2 + "%'");
			sb.append(" and ");
			request.setAttribute("quyu2", quyu2);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}
		
		
		if (shenhezhuangtai != null && !"".equals(shenhezhuangtai)) {

			sb.append(" shenhezhuangtai like '%" + shenhezhuangtai + "%'");
			sb.append(" and ");
			request.setAttribute("shenhezhuangtai", shenhezhuangtai);
		}

	

		sb.append("  1=1 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = diaochuDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", diaochuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!diaochulist3", "共有" + total + "条记录"));
		request.setAttribute("url", "method!diaochulist3");
		request.setAttribute("url2", "method!diaochu");
		this.setUrl("diaochu/diaochulist3.jsp");
		return SUCCESS;

	}
	
	
	//跳转到审核内部调入单页面
	public String diaochuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);		
		
		this.setUrl("diaochu/diaochuupdate5.jsp");
		return SUCCESS;
	}
	
	
	//审核移库单操作
	public void diaochuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String shenhezhuangtai = request.getParameter("shenhezhuangtai");
		String shenhefanhui = request.getParameter("shenhefanhui");
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		Kufang kufang = kufangDao.selectBean(" where id= "+bean.getKufang2().getId());
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setShenheuser(user);
		
		
		if(bean.getDiaodushuliang()> (kufang.getZuidashuliang()-kufang.getRushushuliang())){
			bean.setShenhefanhui("调度数量大于调入库房剩余入库数量");
			bean.setShenhezhuangtai("审核未通过");
			diaochuDao.updateBean(bean);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('调度数量大于调入库房剩余入库数量，审核未通过');window.location.href='method!diaochuupdate5?id="+bean.getId()+"';</script>");
			return;
		}
		
		if("审核通过".equals(shenhezhuangtai)){
			Product product = productDao.selectBean(" where id= "+bean.getProduct().getId());//调度的产品
			Kufang kufang1 = kufangDao.selectBean(" where id= "+bean.getKufang1().getId());//调出库房
			Kufang kufang2 = kufangDao.selectBean(" where id= "+bean.getKufang2().getId());//调入库房
			Kucun kucun = kucunDao.selectBean(" where kufang.id= "+kufang1.getId()+ 
					" and product.id= "+product.getId() 
					+" and  user.id="+bean.getUser().getId()+" and quyu.id= "+bean.getQuyu1().getId());//产品在调出库房的库存
			if(kucun==null){
				bean.setShenhefanhui("调出库房没有该产品，调度失败");
				bean.setShenhezhuangtai("审核未通过");
				diaochuDao.updateBean(bean);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
				response
						.getWriter()
						.print(
								"<script language=javascript>alert('调出库房没有该产品，调度失败');window.location.href='method!diaochuupdate5?id="+bean.getId()+"';</script>");
				return;
				
				
			}
			if(kucun.getShuliang()<bean.getDiaodushuliang()){
				bean.setShenhefanhui("调出库房该产品的数量不够，调度失败");
				bean.setShenhezhuangtai("审核未通过");
				diaochuDao.updateBean(bean);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
				response
						.getWriter()
						.print(
								"<script language=javascript>alert('调出库房该产品的数量不够，调度失败');window.location.href='method!diaochuupdate5?id="+bean.getId()+"';</script>");
				return;
			}
			kufang1.setRushushuliang(kufang1.getRushushuliang()-bean.getDiaodushuliang());
			kufang2.setRushushuliang(kufang2.getRushushuliang()+bean.getDiaodushuliang());
			kufangDao.updateBean(kufang1);
			kufangDao.updateBean(kufang2);

		}
		bean.setShenhefanhui(shenhefanhui);
		bean.setShenhezhuangtai(shenhezhuangtai);
		diaochuDao.updateBean(bean);

		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!diaochulist3';</script>");
	}
	
	
	//确认调出操作
	public void diaochudelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Diaochu bean = diaochuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		bean.setShenhezhuangtai("成功调度");
		diaochuDao.updateBean(bean);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
	
		
		Kucun kucun1 = kucunDao.selectBean(" where kufang.id= "+bean.getKufang1().getId() 
				+" and product.id= "+bean.getProduct().getId() +" and user.id="+user.getId()+" and  quyu.id="+bean.getQuyu1().getId());//调出的库存
		Kucun kucun2 = kucunDao.selectBean(" where kufang.id= "+bean.getKufang2().getId() 
				+" and product.id= "+bean.getProduct().getId() +" and user.id="+user.getId()+" and  quyu.id="+bean.getQuyu2().getId());//调入的库存
		if(kucun2==null){
			kucun2 = new Kucun();
			kucun2.setQuyu(quyuDao.selectBean(" where id= "+bean.getQuyu2().getId()));
			kucun2.setKufang(bean.getKufang2());
			kucun2.setProduct(bean.getProduct());
			kucun2.setShuliang(bean.getDiaodushuliang());
			kucun2.setUser(user);
			kucun1.setShuliang(kucun1.getShuliang()-bean.getDiaodushuliang());
			kucunDao.updateBean(kucun1);
			kucunDao.insertBean(kucun2);
			
			
			
			this.addMingxi(bean.getProduct().getName(), "产品移库", bean.getRukuliyou().getLiyou(), 
					bean.getDiaodushuliang(), user,"产品从"+bean.getKufang1().getName()+"的"+bean.getQuyu1().getQuyuming()+"调入"
					+bean.getKufang2().getName()+"的"+bean.getQuyu2().getQuyuming(),bean.getShenheuser().getUsername(),bean.getProduct().getGongyingshang(),null);
			
			
		}else{
			kucun1.setShuliang(kucun1.getShuliang()-bean.getDiaodushuliang());
			kucun2.setShuliang(kucun2.getShuliang()+bean.getDiaodushuliang());
			kucunDao.updateBean(kucun1);
			kucunDao.updateBean(kucun2);
			
			this.addMingxi(bean.getProduct().getName(), "产品移库", bean.getRukuliyou().getLiyou(), 
					bean.getDiaodushuliang(), user,"产品从"+bean.getKufang1().getName()+"的"+bean.getQuyu1().getQuyuming()+"调入"
					+bean.getKufang2().getName()+"的"+bean.getQuyu2().getQuyuming(),bean.getShenheuser().getUsername(),bean.getProduct().getGongyingshang(),null);
			
		}

		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!diaochulist2';</script>");
	}
	
	
	//动态库查询
	public String kucunlist5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 "));
		request.setAttribute("productlist", productDao.selectBeanList(0, 9999, " where productlock=0 "));
		request.setAttribute("quyulist", quyuDao.selectBeanList(0, 9999, " where quyulock=0 "));
		
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String quyu = request.getParameter("quyu");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+" order by kufang.id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kucunDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", kucunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kucunlist5", "共有" + total + "条记录"));
		request.setAttribute("url", "method!kucunlist5");
		request.setAttribute("url2", "method!kucun");
		this.setUrl("kucun/kucunlist5.jsp");
		return SUCCESS;

	}
	
	
	//库存盘点
	@SuppressWarnings("unchecked")
	public String kucunlist6() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String product = request.getParameter("product");


		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(shuliang),product.name from Kucun where ");
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+" group by product.name ");
		String sql = sb.toString();
		

		
		List list = kucunDao.execSQL(sql);
		List<Vo> vos = new ArrayList<Vo>();
		for(int i=0;i<list.size();i++){
			Object[] o = (Object[])list.get(i);
			Vo vo = new Vo();
			for(int j=0;j<o.length;j++){
				if(j==0){
					vo.setInfo1((Long)o[j]+"");
				}
				if(j==1){
					vo.setInfo2((String)o[j]+"");
				}
				
			}
			vo.setId(i+1);
			vos.add(vo);
		}
		

		request.setAttribute("list",vos );
		
		this.setUrl("kucun/kucunlist6.jsp");
		return SUCCESS;

	}
	
	private MingxiDao mingxiDao;

	public MingxiDao getMingxiDao() {
		return mingxiDao;
	}

	public void setMingxiDao(MingxiDao mingxiDao) {
		this.mingxiDao = mingxiDao;
	}
	
	
	private void addMingxi(String chanpin,String leixing,String liyou,int shuliang,User user,String content,
			String shenheren,String gongyingshang,String jingxiaoshang){
		Mingxi bean = new Mingxi();
		bean.setChanpin(chanpin);
		bean.setCreatetime(new Date());
		bean.setLeixing(leixing);
		bean.setLiyou(liyou);
		bean.setShuliang(shuliang);
		bean.setUser(user);
		bean.setContent(content);
		bean.setShenheren(shenheren);
		bean.setGongyingshang(gongyingshang);
		bean.setJingxiaoshang(jingxiaoshang);
		mingxiDao.insertBean(bean);
		
	}
	
	
	
	//出入库明细查询
	public String mingxilist() {
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String leixing = request.getParameter("leixing");
		String chanpin = request.getParameter("chanpin");
		String gongyingshang = request.getParameter("gongyingshang");
		String jingxiaoshang = request.getParameter("jingxiaoshang");
		
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		
		
		if (gongyingshang != null && !"".equals(gongyingshang)) {

			sb.append(" gongyingshang like '%" + gongyingshang + "%'");
			sb.append(" and ");
			request.setAttribute("gongyingshang", gongyingshang);
		}
		if (jingxiaoshang != null && !"".equals(jingxiaoshang)) {

			sb.append(" jingxiaoshang like '%" + jingxiaoshang + "%'");
			sb.append(" and ");
			request.setAttribute("jingxiaoshang", jingxiaoshang);
		}
		
		if (leixing != null && !"".equals(leixing)) {

			sb.append(" leixing like '%" + leixing + "%'");
			sb.append(" and ");
			request.setAttribute("leixing", leixing);
		}
		
		if (chanpin != null && !"".equals(chanpin)) {

			sb.append(" chanpin like '%" + chanpin + "%'");
			sb.append(" and ");
			request.setAttribute("chanpin", chanpin);
		}
		
		if (time1 != null && !"".equals(time1)) {

			sb.append(" createtime >  '" + time1 + "'");
			sb.append(" and ");
			request.setAttribute("time1", time1);
		}
		
		if (time2 != null && !"".equals(time2)) {

			sb.append(" createtime <=  '" + time2 + "'");
			sb.append(" and ");
			request.setAttribute("time2", time2);
		}
		
		
		
		

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		sb.append("  user.id="+user.getId()+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = mingxiDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!mingxilist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!mingxilist");
		request.setAttribute("url2", "method!mingxi");
		this.setUrl("mingxi/mingxilist.jsp");
		return SUCCESS;

	}
	
	
	//动态库查询(管理员权限)
	public String kucunlist7() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("kufanglist", kufangDao.selectBeanList(0, 9999, " where kufanglock=0 "));
		request.setAttribute("productlist", productDao.selectBeanList(0, 9999, " where productlock=0 "));
		request.setAttribute("quyulist", quyuDao.selectBeanList(0, 9999, " where quyulock=0 "));
		
		String kufang = request.getParameter("kufang");
		String product = request.getParameter("product");
		String quyu = request.getParameter("quyu");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" kufang.name like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (quyu != null && !"".equals(quyu)) {

			sb.append(" quyu.quyuming like '%" + quyu + "%'");
			sb.append(" and ");
			request.setAttribute("quyu", quyu);
		}
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		

		sb.append("  1=1 order by kufang.id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kucunDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", kucunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kucunlist7", "共有" + total + "条记录"));
		request.setAttribute("url", "method!kucunlist7");
		request.setAttribute("url2", "method!kucun");
		this.setUrl("kucun/kucunlist7.jsp");
		return SUCCESS;

	}
	
	
	//出入库明细查询(管理员权限)
	public String mingxilist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String leixing = request.getParameter("leixing");
		String chanpin = request.getParameter("chanpin");
		String gongyingshang = request.getParameter("gongyingshang");
		String jingxiaoshang = request.getParameter("jingxiaoshang");
		
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		
		String username = request.getParameter("username");
		
		String kufang = request.getParameter("kufang");


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		
		if (gongyingshang != null && !"".equals(gongyingshang)) {

			sb.append(" gongyingshang like '%" + gongyingshang + "%'");
			sb.append(" and ");
			request.setAttribute("gongyingshang", gongyingshang);
		}
		if (jingxiaoshang != null && !"".equals(jingxiaoshang)) {

			sb.append(" jingxiaoshang like '%" + jingxiaoshang + "%'");
			sb.append(" and ");
			request.setAttribute("jingxiaoshang", jingxiaoshang);
		}
		
		
		if (kufang != null && !"".equals(kufang)) {

			sb.append(" content like '%" + kufang + "%'");
			sb.append(" and ");
			request.setAttribute("kufang", kufang);
		}
		
		if (leixing != null && !"".equals(leixing)) {

			sb.append(" leixing like '%" + leixing + "%'");
			sb.append(" and ");
			request.setAttribute("leixing", leixing);
		}
		
		if (username != null && !"".equals(username)) {

			sb.append(" user.username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		
		if (chanpin != null && !"".equals(chanpin)) {

			sb.append(" chanpin like '%" + chanpin + "%'");
			sb.append(" and ");
			request.setAttribute("chanpin", chanpin);
		}
		
		if (time1 != null && !"".equals(time1)) {

			sb.append(" createtime >  '" + time1 + "'");
			sb.append(" and ");
			request.setAttribute("time1", time1);
		}
		
		if (time2 != null && !"".equals(time2)) {

			sb.append(" createtime <=  '" + time2 + "'");
			sb.append(" and ");
			request.setAttribute("time2", time2);
		}
		
		
		
		

	

		sb.append("  1=1 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = mingxiDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", mingxiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!mingxilist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!mingxilist2");

		this.setUrl("mingxi/mingxilist2.jsp");
		return SUCCESS;

	}
	
	//库存盘点
	@SuppressWarnings("unchecked")
	public String kucunlist8() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String product = request.getParameter("product");


		StringBuffer sb = new StringBuffer();
		sb.append(" select sum(shuliang),product.name from Kucun where ");
		
		
		
		if (product != null && !"".equals(product)) {

			sb.append(" product.name like '%" + product + "%'");
			sb.append(" and ");
			request.setAttribute("product", product);
		}

		

		sb.append("  1=1 group by product.name ");
		String sql = sb.toString();
		

		
		List list = kucunDao.execSQL(sql);
		List<Vo> vos = new ArrayList<Vo>();
		for(int i=0;i<list.size();i++){
			Object[] o = (Object[])list.get(i);
			Vo vo = new Vo();
			for(int j=0;j<o.length;j++){
				if(j==0){
					vo.setInfo1((Long)o[j]+"");
				}
				if(j==1){
					vo.setInfo2((String)o[j]+"");
				}
				
			}
			vo.setId(i+1);
			vos.add(vo);
		}
		

		request.setAttribute("list",vos );
		
		this.setUrl("kucun/kucunlist8.jsp");
		return SUCCESS;

	}
	
	
	private BaojingDao baojingDao;

	public BaojingDao getBaojingDao() {
		return baojingDao;
	}

	public void setBaojingDao(BaojingDao baojingDao) {
		this.baojingDao = baojingDao;
	}
	
	
	
	//报警列表
	public String baojinglist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<Baojing> baojings = baojingDao.selectBeanList(0, 9999, " where 1=1 ");
		for(Baojing baojing:baojings){
			baojingDao.deleteBean(baojing);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String s = "";
		if(user.getRole()==0){
		
			
			s = " and user.id= "+user.getId();
		}
		
		List<Kufang> kufangs = kufangDao.selectBeanList(0, 9999, " where kufanglock=0 ");
		for(Kufang kufang:kufangs){
			if(kufang.getRushushuliang()<kufang.getBaojingzuixiaoshuliang()){
				Baojing baojing = new Baojing();
				baojing.setContent(kufang.getName()+"的入库数量小于该库房的最小入库数量。报警！！");
				baojingDao.insertBean(baojing);
			}
			if(kufang.getRushushuliang()>kufang.getBaojingzuidashuliang()){
				Baojing baojing = new Baojing();
				baojing.setContent(kufang.getName()+"的入库数量大于该库房的最大入库数量。报警！！");
				baojingDao.insertBean(baojing);
			}
		}
		
		List<Product> products = productDao.selectBeanList(0, 9999, " where  productlock=0 " +s);
		for(Product product :products){
			if(product.getKucunshuliang()<product.getBaojingxiaxian()){
				Baojing baojing = new Baojing();
				baojing.setContent(product.getUser().getUsername()+"添加的"+product.getName()+"的库存数量小于该商品的最小库存数量。报警！！");
				baojingDao.insertBean(baojing);
			}
			
		}
		

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		
		

		sb.append(" 1=1  order by id desc ");
		String where = sb.toString();
		
		
		
		

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = baojingDao.selectBeanCount(" where 1=1 ");
		request.setAttribute("list", baojingDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!baojinglist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!baojinglist");
		request.setAttribute("url2", "method!baojing");
		this.setUrl("baojing/baojinglist.jsp");
		return SUCCESS;

	}
	
	
	private QuyuDao quyuDao;

	public QuyuDao getQuyuDao() {
		return quyuDao;
	}

	public void setQuyuDao(QuyuDao quyuDao) {
		this.quyuDao = quyuDao;
	}
	
	
	
	//区域列表
	public String quyulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String kufangid = request.getParameter("kufangid");
		request.setAttribute("kufangid", kufangid);
		

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		
		sb.append(" quyulock=0  and kufang.id="+kufangid+" order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = quyuDao.selectBeanCount(" where quyulock=0 and kufang.id="+kufangid);
		request.setAttribute("list", quyuDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!quyulist?kufangid="+kufangid, "共有" + total + "条记录"));
		request.setAttribute("url", "method!quyulist");
		request.setAttribute("url2", "method!quyu");
		this.setUrl("quyu/quyulist.jsp");
		return SUCCESS;

	}
//跳转到添加区域页面
	public String quyuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufangid = request.getParameter("kufangid");
		Kufang kufang = kufangDao.selectBean(" where id= "+kufangid);
		
		request.setAttribute("kufang", kufang);
		this.setUrl("quyu/quyuadd.jsp");
		return SUCCESS;
	}
//添加区域操作
	public void quyuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String kufangid = request.getParameter("kufangid");
		String quyuming = request.getParameter("quyuming");
		
		Quyu bean = new Quyu();
		bean.setKufang(kufangDao.selectBean(" where id= "+kufangid));
		bean.setQuyuming(quyuming);
		quyuDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!quyulist?kufangid="+kufangid+"';</script>");
		

	}
//跳转到更新区域页面
	public String quyuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Quyu bean = quyuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("quyu/quyuupdate.jsp");
		return SUCCESS;
	}
//更新区域操作
	public void quyuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String quyuming = request.getParameter("quyuming");
		Quyu bean = quyuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setQuyuming(quyuming);
		quyuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!quyulist?kufangid="+bean.getKufang().getId()+"';</script>");
	}
//删除区域操作
	public void quyudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Quyu bean = quyuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setQuyulock(1);
		quyuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!quyulist?kufangid="+bean.getKufang().getId()+"';</script>");
	}
	
	//跳转到查看区域页面
	public String quyuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Quyu bean = quyuDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("quyu/quyuupdate3.jsp");
		return SUCCESS;
	}
	
	
	//获取库房区域信息
	public String getcate() throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/xml");
    	response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	    response.setCharacterEncoding("UTF-8");

    	String where = " where quyulock=0 and kufang.id= " +request.getParameter("pid");
    	List<Quyu> list = quyuDao.selectBeanList(0, 9999, where);
    	String xml_start = "<selects>";
        String xml_end = "</selects>";
        String xml = "";
        for(int i=0;i<list.size();i++){
        	xml+="<select><value>"+list.get(i).getId()+"</value><text>"+list.get(i).getQuyuming()+"</text></select>";
        }
        String last_xml = xml_start + xml + xml_end;
        response.getWriter().write(last_xml);
        return null;
    }
	

	
	//仓库主管列表
	public String userlist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");
	


		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		

		sb.append(" userlock=0  and role=2 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(" where userlock=0 and role=2");
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist2");
		request.setAttribute("url2", "method!user");
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;

	}
//跳转到添加仓库主管页面
	public String useradd3() {
		this.setUrl("user/useradd3.jsp");
		return SUCCESS;
	}
//添加仓库主管操作
	public void useradd4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");
		String username = request.getParameter("username");
		int count = userDao.selectBeanCount(" where userlock=0 and role=2 ");
		if(count>0){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('仓库主管已经存在，注册失败');window.location.href='method!userlist2';</script>");
			return;
		}
		
		User bean = userDao.selectBean(" where username='"+username+"' and userlock=0 and role=2 ");
		if(bean==null){
			bean = new User();
			bean.setCreatetime(new Date());
			bean.setPassword("111111");
			bean.setTruename(truename);
			bean.setUsername(username);
			bean.setRole(2);
			userDao.insertBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作成功');window.location.href='method!userlist2';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名已经存在，注册失败');window.location.href='method!userlist2';</script>");
		}
		
		

	}
//跳转到更新仓库主管页面
	public String userupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate5.jsp");
		return SUCCESS;
	}
//更新仓库主管操作
	public void userupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");


		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setTruename(truename);
		
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist2';</script>");
	}
//删除仓库主管操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setUserlock(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");;
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist2';</script>");
	}
	
	//跳转到查看仓库主管页面
	public String userupdate7() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("user/userupdate7.jsp");
		return SUCCESS;
	}
	
	

}
