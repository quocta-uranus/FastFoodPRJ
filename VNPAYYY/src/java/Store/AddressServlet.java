package java.Store;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String action = request.getParameter("action");
            if ("getDistrict".equals(action)) {
                String idProvince = request.getParameter("idProvince");
                JsonArray districtList = getDistrictsForProvince(idProvince);
                response.getWriter().write(districtList.toString());
            } else if ("getCommune".equals(action)) {
                String idDistrict = request.getParameter("idDistrict");
                JsonArray communeList = getCommunesForDistrict(idDistrict);
                response.getWriter().write(communeList.toString());
            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage);

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }

    }

    private JsonArray getDistrictsForProvince(String idProvince) throws IOException {
        String apiUrl = "https://vietnam-administrative-division-json-server-swart.vercel.app";
        String apiEndpoint = apiUrl + "/district/?idProvince=" + idProvince;

        HttpURLConnection connection = (HttpURLConnection) new URL(apiEndpoint).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();
        connection.disconnect();

        JsonArray districtList = new JsonParser().parse(jsonResponse.toString()).getAsJsonArray();
        return districtList;
    }

    private JsonArray getCommunesForDistrict(String idDistrict) throws IOException {
        String apiUrl = "https://vietnam-administrative-division-json-server-swart.vercel.app";
        String apiEndpoint = apiUrl + "/commune/?idDistrict=" + idDistrict;

        HttpURLConnection connection = (HttpURLConnection) new URL(apiEndpoint).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();
        connection.disconnect();

        JsonArray communeList = new JsonParser().parse(jsonResponse.toString()).getAsJsonArray();
        return communeList;
    }
}