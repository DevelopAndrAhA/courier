package kg.bish.courier.controllers;

import kg.bish.courier.Config;
import kg.bish.courier.service.MyService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping
public class OutputPhoto {
    @Autowired
    MyService myServiceClass;
    Config config = new Config();
    @ResponseBody
    @RequestMapping(value = "large_photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] photo_company(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = config.getAdvPath()+File.separator+"path"+File.separator+"adv"+File.separator+companyId+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
    @ResponseBody
    @RequestMapping(value = "small_photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] small_photo_company(@RequestParam("companyId")String companyId,@RequestParam("imgName") String imgName) throws IOException {
        InputStream in = null;
        String rootPath = config.getAdvPath()+File.separator+"path"+File.separator+"adv"+File.separator+companyId+File.separator+"small"+File.separator+imgName;
        File dir = new File(rootPath + File.separator);
        File f = new File(dir.getAbsolutePath());
        in = new FileInputStream(f);
        return IOUtils.toByteArray(in);
    }
}
