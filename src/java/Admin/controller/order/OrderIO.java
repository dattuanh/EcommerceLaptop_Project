/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.controller.order;

import Base.Dal.DAOOrder;
import Base.Model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 *
 * @author Giang Minh
 */
@WebServlet(name = "OrderIO", urlPatterns = {"/OrderIO"})
public class OrderIO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderIO</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderIO at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            export(response);
            response.sendRedirect("OrderManagement");
        } catch (Exception e) {
            System.out.println("OrderIODoGet: " + e.getMessage());
        }
    }

    private boolean export(HttpServletResponse response) {
        DAOOrder daoOrder = new DAOOrder();
        final List<Order> orders = daoOrder.getAllOrders();
        String arr[] = {"Order Id", "CustomerID", "OrderDate", "TotalPrice", "OrderStatus",
            "PaymentDate", "PaymentMethod", "CashReceive", "CashBack", "Address"};
        try ( XSSFWorkbook wk = new XSSFWorkbook();  ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            XSSFSheet sheet = wk.createSheet("Danh Sach Don Hang");
            XSSFRow row = sheet.createRow(0); // create row 0 for sheet name 'danhsach'
            XSSFCellStyle style = wk.createCellStyle();
            style.setAlignment(HorizontalAlignment.LEFT);
            Cell cell;

            DataFormat dataFormat = wk.createDataFormat();
            CellStyle dateCellStyle = wk.createCellStyle();
            dateCellStyle.setDataFormat(dataFormat.getFormat("dd/MM/yyyy"));

            for (int i = 0; i < arr.length; i++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(arr[i]);
            }

            for (int i = 0; i < orders.size(); i++) {
                row = sheet.createRow(i + 2);
                Order o = orders.get(i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(o.getOrderId());
                cell.setCellStyle(style);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(o.getCustomer().getUserName());
                cell.setCellStyle(style);

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(o.getOrderDate());
                cell.setCellStyle(dateCellStyle);
                //cell.setCellStyle(style);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(o.getTotalPrice());
                cell.setCellStyle(style);

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(o.getStatus().getOrderStatusName());
                cell.setCellStyle(style);

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(o.getPaymentDate());
                cell.setCellStyle(dateCellStyle);
                //cell.setCellStyle(style);

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(o.getPaymentMethod());
                cell.setCellStyle(style);

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(o.getCashReceive());
                cell.setCellStyle(style);

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(o.getCashBack());
                cell.setCellStyle(style);

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(o.getAddress());
                cell.setCellStyle(style);
                sheet.autoSizeColumn(i);
            }

//            File f = new File(PATH_BAITHI);
            wk.write(baos);

            // Set the response headers for file download
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"danh_sach_don_hang.xlsx\"");

            // Write the file content to the response output stream
            try ( OutputStream out = response.getOutputStream()) {
                baos.writeTo(out);
            }

            System.out.println("export success");
        } catch (Exception e) {
            System.out.println("Export_DanhSachDonHang: e........................................" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
