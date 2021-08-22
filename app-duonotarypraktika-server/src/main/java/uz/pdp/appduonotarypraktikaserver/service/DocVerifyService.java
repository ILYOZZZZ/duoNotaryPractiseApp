package uz.pdp.appduonotarypraktikaserver.service;


import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Order;
import uz.pdp.appduonotarypraktikaserver.entity.State;
import uz.pdp.appduonotarypraktikaserver.payload.ResDocArray;
import uz.pdp.appduonotarypraktikaserver.repository.OrderRepository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocVerifyService {


     //video   vizov
    @Autowired
    OrderRepository orderRepository;


    public ResponseEntity<byte[]> getOneDoc(boolean download, String docId) {
        try {
            URL url = new URL("https://api.docverify.com/V2/dvapi.asmx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "   <GetDocument xmlns=\"https://api.docverify.com/\">\n" +
                    "      <apiKey>upbvpmZu5eevouSJute8XqFlFZf6gE4Z</apiKey>\n" +
                    "      <apiSig>447194D52559259982BE55B7895BD843</apiSig>\n" +
                    "      <DocVerifyID>"+docId+"</DocVerifyID>\n" +
                    "    </GetDocument>\n" +
                    "     </soap:Body>\n" +
                    "</soap:Envelope>");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            outputStream.close();
            connection.connect();

            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            int result2 = bis.read();

            while (result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();

            }

            String responseBody = buf.toString();

            JSONObject xmlJSONObj = XML.toJSONObject(responseBody);


            String bytes = xmlJSONObj
                    .getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("GetDocumentResponse")
                    .getString("GetDocumentResult");

            if (download){
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" codelari.pdf")
                        .body(Base64.decodeBase64(bytes));
            }else {
                HttpHeaders header = new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_PDF);
                return new ResponseEntity<>(Base64.decodeBase64(bytes), header, HttpStatus.OK);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public HttpEntity<?> getDoc(boolean download, String orderId, String docId) {
        if (!docId.equals("all")){
          return getOneDoc(download,docId);
        }
            Optional<Order> byId = orderRepository.findById(UUID.fromString(orderId));
            if (byId.isPresent()){
                Order order = byId.get();
                if (order.isDocPackage()){
                    return ResponseEntity.ok(getPacket(order.getDocVerifyId()));
                }else {
                   return getOneDoc(download,order.getDocVerifyId());
                }
            }
        return null;
    }
    public List<ResDocArray> getPacket(String packetId){
        try {
            URL url = new URL("https://api.docverify.com/V2/dvapi.asmx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "   <GetPacketDocs xmlns=\"https://api.docverify.com/\">\n" +
                    "      <apiKey>upbvpmZu5eevouSJute8XqFlFZf6gE4Z</apiKey>\n" +
                    "      <apiSig>447194D52559259982BE55B7895BD843</apiSig>\n" +
                    "      <PacketID>"+packetId+"</PacketID>\n" +
                    "    </GetPacketDocs>\n" +
                    "     </soap:Body>\n" +
                    "</soap:Envelope>");
            outputStream.flush();
            outputStreamWriter.close();
            outputStream.close();
            connection.connect();

            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            int result2 = bis.read();

            while (result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();

            }

            String responseBody = buf.toString();

            JSONObject xmlJSONObj = XML.toJSONObject(responseBody);
            System.out.println(xmlJSONObj);

            String array = xmlJSONObj
                    .getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("GetPacketDocsResponse")
                    .getString("GetPacketDocsResult");
            String[] split = array.substring(1, array.length() - 1).split("},");
            List<ResDocArray> resDocArrays = new ArrayList<>();
            for (String s : split) {
                s=s.endsWith("}")?s : s+"}";
                JSONObject jsonObject = new JSONObject(s);
                ResDocArray resDocArray = new ResDocArray();
                resDocArray.setName(jsonObject.getString("Name"));
                resDocArray.setDocVerifyId(jsonObject.getString("DocVerifyID"));
                resDocArrays.add(resDocArray);
            }

            return resDocArrays;
////
//            if (download){
//                return ResponseEntity.ok()
//                        .contentType(MediaType.APPLICATION_PDF)
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" codelari.pdf")
//                        .body(Base64.decodeBase64(bytes));
//            }else {
//                HttpHeaders header = new HttpHeaders();
//                header.setContentType(MediaType.APPLICATION_PDF);
//                return new ResponseEntity<>(Base64.decodeBase64(bytes), header, HttpStatus.OK);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
