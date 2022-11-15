/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwsidcard.c.idcard.c;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Reza Ramadhani
 */
@Controller
public class controller {
    @RequestMapping("/getData")
    public String getData(@RequestParam("namalengkap")String text,
                          @RequestParam("tanggallahir")@DateTimeFormat(pattern="yyyy-MM-dd")Date date,
                          @RequestParam("foto")MultipartFile file, Model model)throws IOException, ParseException{
        
        SimpleDateFormat tanggal = new SimpleDateFormat("EE/dd/MMMM/yyyy");
        
        String nTgl = tanggal.format(date);
        
        String blob = Base64.encodeBase64String(file.getBytes());
        String photo = "data:image/jpeg;base64,".concat(blob);
        
        model.addAttribute("namalengkap", text);
        model.addAttribute("tanggallahir", nTgl);
        model.addAttribute("foto", photo); 
        
        
        return "output";
        
    }

}
