package com.fancyfrog.imageUploading.rest;

import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file-upload")
@Api(value = "Guidelines",
     description = "Describes the guidelines for Spring boot 2.0.1 for uploading large file using Swagger UI")
public class ImageUploadingResource {

    @PostMapping
    @ApiOperation(value = "Make a POST request to upload the file",
                  produces = "application/json",
                  consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiResponses(value =  {
            @ApiResponse(code = 200,message = "The POST call is success full"),
            @ApiResponse(code = 500,message = "The POST call is failed"),
            @ApiResponse(code = 404,message = "The API could not be found")
    })
    public ResponseEntity<?> upload(
            @ApiParam(name = "file",value = "Select the file to upload",required = true)
            @RequestPart("file")MultipartFile file){

        try{
            String fileName = file.getOriginalFilename();
            InputStream fileStream = file.getInputStream();
            File targetFile = new File("C:\\Users\\QuaQUa\\Desktop\\test" + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
            FileUtils.copyInputStreamToFile(fileStream,targetFile);
            resize(targetFile.getAbsolutePath(),500,500);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done",HttpStatus.OK);
    }

    private void resize(String pathName,int height,int width) throws IOException {
        File input = new File(pathName);
        BufferedImage image = ImageIO.read(input);
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        File output = new File("C:\\Users\\QuaQUa\\Desktop\\duke-resized-500x500.png");
        ImageIO.write(resized, "png", output);
    }
}
