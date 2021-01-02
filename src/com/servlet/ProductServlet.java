package com.servlet;

import com.bean.*;
import com.bean.Product;
import com.dao.BudgetDAO;
import com.dao.ProductDAO;
import com.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        PrintWriter writer = null;
        if("add".equals(method)){
            //封装对象
            Product product = requestDataObj(request);
            ProductDAO productDAO = new ProductDAO();
            //将对象保存到数据库
            boolean flag = productDAO.saveProduct(product);
            if(flag){
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('添加成功！');");
                writer.write("window.location.href='index.jsp###'");
                writer.write("</script>");
            }else{
                writer = response.getWriter();
                writer.write("<script>");
                writer.write("alert('已存在该产品编号！');");
                writer.write("window.location.href='index.jsp#ap'");
                writer.write("</script>");
            }
            writer.flush();
            writer.close();
        }else if(method.equals("show")){
            String pageSizeStr = request.getParameter("pageSize");
            Integer pageSize = null;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.valueOf(pageSizeStr);
            } else {
                pageSize = 10;
            }
            //2.当前第几页 currentPage
            String currentPageStr = request.getParameter("currentPage");
            Integer currentPage = null;
            if (currentPageStr != null && currentPageStr.length() > 0) {
                currentPage = Integer.valueOf(currentPageStr);
            } else {
                currentPage = 1;
            }
            //3.一共有多上行数据 totalRows
            Integer totalRows = 0;
            ProductDAO productDAO = new ProductDAO();
            totalRows = productDAO.getProductCount();


            //limit startRow,pageSize;
            //5.起始行 startRow
            Integer startRow = (currentPage - 1) * pageSize;

            StringBuffer sqlRow = new StringBuffer(
                    "select * from product"
            );
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

            List<Product> productList = productDAO.getProductListByPage(sqlRow.toString());

            PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, productList);

            request.setAttribute("pageBean", pageBean);

            request.getRequestDispatcher("productList.jsp").forward(request, response);
        }
    }

    //获取页面数据，封装成一个Product对象
    private Product requestDataObj(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productYieldStr = request.getParameter("productYield");

        double productYield = Double.parseDouble(productYieldStr);
        Product product = new Product(productId,productName,productYield);
        return product;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
