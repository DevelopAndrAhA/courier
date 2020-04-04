package kg.bish.courier.controllers;

import kg.bish.courier.Config;
import kg.bish.courier.models_to_db.*;
import kg.bish.courier.models_to_serialize.UniverseJSON;
import kg.bish.courier.service.MyService;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ALTYNBEK on 05.02.2020.
 */
@Controller
@RequestMapping("adverst")
public class AdvertisingController {
    @Autowired
    MyService myService;

    Config config = new Config();

    /*@ResponseBody
    @RequestMapping(value = "save_adverst",method = RequestMethod.POST)
    public Object save_adverst(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("price") String price,
            @RequestParam("secretKey") String secretKey,
            @RequestParam("weigth") String weigth,
            @RequestParam("statusStr") String statusStr*//*,@RequestParam("file") MultipartFile photo*//*) {
        if(secretKey!=null&&!secretKey.isEmpty()&&statusStr!=null&&!statusStr.isEmpty()){
            if(statusStr.equals("COURIER")){
                CourierDB courierDB = new CourierDB();
                courierDB.setId(id);
                courierDB.setSecretKey(secretKey);
                boolean res = myService.verifySecretKey(courierDB);
                if(!res){
                    return "{'secretKey':false}";
                }
            }else if(statusStr.equals("CLIENT")){
                ClientDB clientDB = new ClientDB();
                clientDB.setId(id);
                clientDB.setSecretKey(secretKey);
                boolean res = myService.verifySecretKey(clientDB);
                if(!res){
                    return "{'secretKey':false}";
                }
            }
        }else{
            return "{'secretKey':false}";
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            *//*if (photo != null && photo.getOriginalFilename() != "") {
                String file_name = null;
                MultipartFile file = photo;
                file_name = photo.getOriginalFilename();
                file_name = FilenameUtils.getExtension(file_name);
                try {
                    byte[] bytes = file.getBytes();
                    String rootPath = config.getAdvPath()+File.separator;
                    File dir = new File(rootPath + File.separator);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    RandomString randomString =new RandomString(30);
                    String randomStr = randomString.nextString();
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + randomStr + "." + file_name);
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stringBuilder.append(randomStr + "." + file_name);
                    stream.close();
                    FileInputStream fis = new FileInputStream(serverFile);
                    BufferedImage bufferedImage = ImageIO.read(fis);
                    BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/3, true);
                    File file2 = new File(rootPath+File.separator+"sm_"+stringBuilder);
                    ImageIO.write(resBufferedImage,file_name,file2);
                } catch (Exception e) {
                    return null;
                }


            } else if (photo == null || photo.getOriginalFilename() == "") {
                return null;
            }*//*
            try {
                if (name != null && name.length() != 0 && !name.equals("")) {
                    AdvertisingDB advertisingDB = new AdvertisingDB();
                    advertisingDB.setName(name);
                    advertisingDB.setLatitude(Double.valueOf(latitude));
                    advertisingDB.setLongitude(Double.valueOf(longitude));
                    advertisingDB.setPrice(Integer.valueOf(price));
                    advertisingDB.setWeight(weigth);
                    advertisingDB.setPhoto(stringBuilder.toString());
                    myService.save(advertisingDB);
                    return "{'success':true}";
                }
            } catch (NullPointerException e) {}
        }catch (NullPointerException e){}
        return "{'success':false}";
}*/
    @ResponseBody
    @RequestMapping(value = "save_adverst",method = RequestMethod.POST)
    public Object save_adverst(@RequestBody Save_adverst save_adverst) {
        long id =  save_adverst.getClientDB().getId();
        String secretKey =  save_adverst.getClientDB().getSecretKey();
        String statusStr =  save_adverst.getClientDB().getStatusStr();
        if(secretKey!=null&&!secretKey.isEmpty()&&statusStr!=null&&!statusStr.isEmpty()){
            ClientDB clientDB = new ClientDB();
            clientDB.setSecretKey(secretKey);
            clientDB.setId(id);
            if(statusStr.equals("COURIER")){
                CourierDB courierDB = new CourierDB();
                courierDB.setId(save_adverst.getClientDB().getId());
                courierDB.setSecretKey(save_adverst.getClientDB().getSecretKey());
                boolean res = myService.verifySecretKey(courierDB);
                if(!res){
                    return "{'secretKey':false}";
                }
            }else if(statusStr.equals("CLIENT")){
                boolean res = myService.verifySecretKey(clientDB);
                if(!res){
                    return "{'secretKey':false}";
                }
            }
        }else{
            return "{'secretKey':false}";
        }
        try {
            AdvertisingDB adverst = save_adverst.getAdvertisingDB();
            List<Product>products = adverst.getProductses();
            double all_price = 0;
            for(int i =0;i<products.size();i++){
                Product product =  products.get(i);
                product.setAdvertisingDB(adverst);
                all_price+=product.getPrice();
            }
            try {
                adverst.setClient_id(id);
                adverst.setAll_price(all_price);
                myService.save(adverst);
                return "{'success':true}";
            } catch (NullPointerException e) {}
        }catch (NullPointerException e){}
        return "{'success':false}";
    }

    @ResponseBody
    @RequestMapping(value = "get_all_adverst",method = RequestMethod.POST)
    public Object get_all_adverst(@RequestBody UniverseJSON universeJSON) {
                if(universeJSON.getStatusStr().equals("CLIENT")){
                    if(!universeJSON.getSecretKey().isEmpty()){
                        if(universeJSON.getCourierClientId()!=0){
                            ClientDB clientDB = new ClientDB();
                            clientDB.setId(universeJSON.getCourierClientId());
                            clientDB.setSecretKey(universeJSON.getSecretKey());
                            if(myService.verifySecretKey(clientDB)){
                                List<AdverstOut>list =  myService. getAllAdverts(universeJSON.getStartId(), universeJSON.getEndId());
                                if(list!=null&&list.size()!=0){
                                    for(int i=0;i<list.size();i++){
                                        AdverstOut adverstOut = list.get(i);
                                        ClientDB clientDB1 =  adverstOut.getClientDB();
                                        clientDB = new ClientDB();
                                        clientDB.setAddress(clientDB1.getAddress());
                                        clientDB.setLatitude(clientDB1.getLatitude());
                                        clientDB.setLongitude(clientDB1.getLongitude());
                                        clientDB.setPhone(clientDB1.getPhone());
                                        adverstOut.setClientDB(clientDB);
                                    }
                                    return list;
                                }else{
                                    return "{'adverst':CLIENT}";
                                }

                            }
                        }
                    }
                }else{
                    CourierDB courierDB = new CourierDB();
                    courierDB.setId(universeJSON.getCourierClientId());
                    courierDB.setSecretKey(universeJSON.getSecretKey());
                    if(myService.verifySecretKey(courierDB)){
                        List<AdverstOut>list = myService. getAllAdverts(universeJSON.getStartId(), universeJSON.getEndId());
                        if(list!=null&&list.size()!=0){
                            for(int i=0;i<list.size();i++){
                                AdverstOut adverstOut = list.get(i);
                                ClientDB clientDB1 =  adverstOut.getClientDB();
                                ClientDB clientDB = new ClientDB();
                                clientDB.setAddress(clientDB1.getAddress());
                                clientDB.setLatitude(clientDB1.getLatitude());
                                clientDB.setLongitude(clientDB1.getLongitude());
                                clientDB.setPhone(clientDB1.getPhone());
                                adverstOut.setClientDB(clientDB);
                            }
                            return list;
                        }else{
                            return "{'adverst':COURIER}";
                        }

                    }
                }
        return "{'adverst':false}";
    }

    @ResponseBody
    @RequestMapping(value = "get_adverst",method = RequestMethod.POST)
    public Object get_adverst(@RequestBody UniverseJSON universeJSON) {
        if(universeJSON.getStatusStr().equals("CLIENT")){
            if(!universeJSON.getSecretKey().isEmpty()){
                if(universeJSON.getCourierClientId()!=0){
                    ClientDB clientDB = new ClientDB();
                    clientDB.setId(universeJSON.getCourierClientId());
                    clientDB.setSecretKey(universeJSON.getSecretKey());
                    if(myService.verifySecretKey(clientDB)){
                        AdvertisingDB advertisingDB =  myService.getAdverts(universeJSON.getAdvId());
                        if(advertisingDB!=null){
                            return advertisingDB;
                        }else{
                            return "{'adverst':false}";
                        }

                    }
                }
            }
        }else{
            CourierDB courierDB = new CourierDB();
            courierDB.setId(universeJSON.getCourierClientId());
            courierDB.setSecretKey(universeJSON.getSecretKey());
            if(myService.verifySecretKey(courierDB)){
                AdvertisingDB advertisingDB =  myService.getAdverts(universeJSON.getAdvId());
                if(advertisingDB!=null){
                    return advertisingDB;
                }else{
                    return "{'adverst':false}";
                }
            }
        }
        return "{'adverst':false}";
    }










    BufferedImage createResizedCopy(Image originalImage,
                                    int scaledWidth, int scaledHeight,
                                    boolean preserveAlpha)
    {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }



}




