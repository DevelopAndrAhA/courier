package kg.bish.courier.controllers;

import kg.bish.courier.Config;
import kg.bish.courier.models_to_db.ClientDB;
import kg.bish.courier.models_to_db.CourierDB;
import kg.bish.courier.models_to_serialize.ClientJSON;
import kg.bish.courier.models_to_serialize.CourierJSON;
import kg.bish.courier.service.MyService;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.*;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    MyService myService;
    Config config = new Config();



    @ResponseBody
    @RequestMapping(value = "courier",method = RequestMethod.POST)
    public Object registrationCorier(@RequestParam("FIO") String FIO,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("login") String login,
                                     @RequestParam("password") String password,
                                     @RequestParam("photo") MultipartFile photo,
                                     @RequestParam("photo_p1") MultipartFile photo_p1,
                                     @RequestParam("photo_p2") MultipartFile photo_p2) {



        try {
            if(!(FIO.isEmpty()
                    &&phone.isEmpty()
                    &&login.isEmpty()
                    &&password.isEmpty()
                    &&photo.getOriginalFilename().isEmpty()
                    &&photo_p1.getOriginalFilename().isEmpty()
                    &&photo_p2.getOriginalFilename().isEmpty())){
                CourierDB courierDB = new CourierDB();
                courierDB.setLogin(login);
                boolean result = myService.verifyLOGIN(courierDB);
                if(result){
                    return "{'result':'login'}";
                }

                long maxCount = 0;
                StringBuilder photoStrBuild = new StringBuilder();
                StringBuilder photoStrBuild_p1 = new StringBuilder();
                StringBuilder photoStrBuild_p2 = new StringBuilder();
                if (photo != null && !photo.getOriginalFilename().isEmpty()) {
                    String file_name = null;
                    MultipartFile file = photo;
                    file_name = photo.getOriginalFilename();
                    file_name = FilenameUtils.getExtension(file_name);
                    try {
                        maxCount = myService.getLastContCourier();
                        maxCount++;
                        byte[] bytes = file.getBytes();
                        String rootPath = config.getCourierPath()+File.separator+maxCount+File.separator;
                        File dir = new File(rootPath + File.separator);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        RandomString randomString =new RandomString(20);

                        String randomStr = randomString.nextString();
                        File serverFile = new File(dir.getAbsolutePath()
                                + File.separator + randomStr + "." + file_name);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        photoStrBuild.append(randomStr + "." + file_name);
                        stream.close();
                        FileInputStream fis = new FileInputStream(serverFile);
                        BufferedImage bufferedImage = ImageIO.read(fis);
                        BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
                        File file1 = new File(rootPath+File.separator+"small"+File.separator);
                        if(!file1.exists()){
                            file1.mkdirs();
                        }
                        File file2 = new File(file1.getAbsolutePath()+File.separator+photoStrBuild);
                        ImageIO.write(resBufferedImage,file_name,file2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "{'result':'photo'}";
                    }


                } else if (photo == null || photo.getOriginalFilename() == "") {
                    return "{'result':'photo'}";
                }
/*
            ===========================================================
*/
                if (photo_p1 != null && !photo_p1.getOriginalFilename().isEmpty()) {
                    String file_name = null;
                    MultipartFile file = photo_p1;
                    file_name = photo_p1.getOriginalFilename();
                    file_name = FilenameUtils.getExtension(file_name);
                    try {
                        byte[] bytes = file.getBytes();
                        String rootPath = config.getCourierPath()+File.separator+maxCount+File.separator;
                        File dir = new File(rootPath + File.separator);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        RandomString randomString =new RandomString(20);
                        String randomStr = randomString.nextString();
                        File serverFile = new File(dir.getAbsolutePath()
                                + File.separator + randomStr + "." + file_name);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        photoStrBuild_p1.append(randomStr + "." + file_name);
                        stream.close();
                        FileInputStream fis = new FileInputStream(serverFile);
                        BufferedImage bufferedImage = ImageIO.read(fis);
                        BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/2, bufferedImage.getHeight()/6, true);
                        File file1 = new File(rootPath+File.separator+"small"+File.separator);
                        if(!file1.exists()){
                            file1.mkdirs();
                        }
                        File file2 = new File(file1.getAbsolutePath()+File.separator+photoStrBuild_p1);
                        ImageIO.write(resBufferedImage,file_name,file2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "{'result':'photo_p1'}";
                    }


                } else if (photo_p1 == null || photo_p1.getOriginalFilename() == "") {
                    return "{'result':'photo_p1'}";
                }
/*
            ===========================================================
*/
                if (photo_p2 != null && !photo_p2.getOriginalFilename().isEmpty()) {
                    String file_name = null;
                    MultipartFile file = photo_p2;
                    file_name = photo_p2.getOriginalFilename();
                    file_name = FilenameUtils.getExtension(file_name);
                    try {
                        byte[] bytes = file.getBytes();
                        String rootPath = config.getCourierPath()+File.separator+maxCount+File.separator;
                        File dir = new File(rootPath + File.separator);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        RandomString randomString =new RandomString(20);
                        String randomStr = randomString.nextString();
                        File serverFile = new File(dir.getAbsolutePath()
                                + File.separator + randomStr + "." + file_name);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        photoStrBuild_p2.append(randomStr + "." + file_name);
                        stream.close();
                        FileInputStream fis = new FileInputStream(serverFile);
                        BufferedImage bufferedImage = ImageIO.read(fis);
                        BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/2, bufferedImage.getHeight()/6, true);
                        File file1 = new File(rootPath+File.separator+"small"+File.separator);
                        if(!file1.exists()){
                            file1.mkdirs();
                        }
                        File file2 = new File(file1.getAbsolutePath()+File.separator+photoStrBuild_p2);
                        ImageIO.write(resBufferedImage,file_name,file2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "{'result':'photo_p2'}";
                    }


                } else if (photo_p2 == null || photo_p2.getOriginalFilename() == "") {
                    return "{'result':'photo_p2'}";
                }
                try {
                    courierDB = new CourierDB();
                    courierDB.setFIO(FIO);
                    courierDB.setLogin(login);
                    courierDB.setPassword(password);
                    courierDB.setPhone(phone);
                    courierDB.setStatusStr("COURIER");
                    courierDB.setPhoto(photoStrBuild.toString());
                    courierDB.setPhoto_p1(photoStrBuild_p1.toString());
                    courierDB.setPhoto_p2(photoStrBuild_p2.toString());
                    RandomString randomString =new RandomString(20);

                    CourierJSON courierJSON = new CourierJSON();
                    courierJSON.setLogin(courierDB.getLogin());
                    courierJSON.setPhone(courierDB.getPhone());
                    courierJSON.setPassword(courierDB.getPassword());
                    courierJSON.setStatusStr(courierDB.getStatusStr());
                    courierJSON.setFIO(courierDB.getFIO());
                    courierJSON.setPhoto(courierDB.getPhoto());
                    courierJSON.setPhoto_p1(courierDB.getPhoto_p1());
                    courierJSON.setPhoto_p2(courierDB.getPhoto_p2());


                    courierDB.setSecretKey(randomString.nextString());
                    courierJSON.setSecretKey(courierDB.getSecretKey());
                    myService.save(courierDB);
                    courierJSON.setId(myService.getSelfId("courierdb", courierJSON.getSecretKey(), courierJSON.getLogin()));

                    return courierJSON;
                } catch (NullPointerException e) {}
            }else{
                return "{'result':'error'}";
            }

        }catch (NullPointerException e){}
        return "{'result':'error'}";
    }




    @ResponseBody
    @RequestMapping(value = "client",method = RequestMethod.POST)
    public Object registrationClient(@RequestParam("phone") String phone,
                                     @RequestParam("login") String login,
                                     @RequestParam("password") String password,
                                     @RequestParam("address") String address,
                                     @RequestParam("latitude") String latitude,
                                     @RequestParam("longitude") String longitude
    ) {

        try{
            if((phone==null||phone.isEmpty())&&(login==null||login.isEmpty())&&(password==null||password.isEmpty())){
                return "{'result':'error'}";
            }
            ClientDB clientDB = new ClientDB();
            clientDB.setLogin(login);
            boolean result = myService.verifyLOGIN(clientDB);
            if(result){
                return "{'result':'login'}";
            }

            clientDB.setPhone(phone);
            clientDB.setPassword(password);
            RandomString randomString = new RandomString(20);
            clientDB.setSecretKey(randomString.nextString());
            clientDB.setStatusStr("CLIENT");

            clientDB.setAddress(address);
            clientDB.setLatitude(latitude);
            clientDB.setLongitude(longitude);
            if(myService.save(clientDB)){
                ClientJSON clientJSON = new ClientJSON();
                clientJSON.setPhone(phone);
                clientJSON.setLogin(login);
                clientJSON.setPassword(password);
                clientJSON.setSecretKey(clientDB.getSecretKey());
                clientJSON.setStatusStr("CLIENT");

                clientJSON.setAddress(address);
                clientJSON.setLatitude(latitude);
                clientJSON.setLongitude(longitude);

                clientJSON.setId(myService.getSelfId("clientdb", clientJSON.getSecretKey(), clientJSON.getLogin()));
                return clientJSON;
            }
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }










        BufferedImage createResizedCopy(Image originalImage,int scaledWidth, int scaledHeight, boolean preserveAlpha)
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
