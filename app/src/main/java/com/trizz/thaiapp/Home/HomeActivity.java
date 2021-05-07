package com.trizz.thaiapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.trizz.thaiapp.Adapter.HomeRecyclerAdapter;
import com.trizz.thaiapp.Model.TopicModel;
import com.trizz.thaiapp.Model.WordModel;
import com.trizz.thaiapp.R;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShape;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView topicRecycler = findViewById(R.id.topic_recycler_view);
        ArrayList<TopicModel> topicList = readExcel(this);
        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(topicList);
        topicRecycler.setAdapter(adapter);
        topicRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setOnItemClickListener(v -> {

        });
    }

    private ArrayList<TopicModel> readExcel(Context context) {
        ArrayList<TopicModel> topicList = new ArrayList<>();
        try {
            InputStream is = context.getApplicationContext().getAssets().open("data.xlsx");
            Workbook workbook = WorkbookFactory.create(is);
            int numberOfSheet = workbook.getNumberOfSheets();
            workbook.getAllPictures();
            int sheetIndex = 0;
            while (sheetIndex < numberOfSheet) {
                ArrayList<WordModel> wordList = new ArrayList<>();
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                sheetIndex++;
                ArrayList<Picture> pictureArrayList = getPictureFromSheet(sheet);
                int index = 0;
                for (Row row : sheet) {
                    String wordText = row.getCell(0).getStringCellValue();
                    String meaning = row.getCell(1).getStringCellValue();
                    Picture pic = index < pictureArrayList.size() ? pictureArrayList.get(index) : null;
                    index++;
                    WordModel word = new WordModel(wordText, meaning, pic);
                    wordList.add(word);
                }
                TopicModel topic = new TopicModel(getTopicImage(sheet.getSheetName()), sheet.getSheetName(), wordList);
                topicList.add(topic);
            }
        } catch (IOException e) {
            Log.d("Error", "Read excel error: " + e);
        } catch (InvalidFormatException x) {
            Log.d("Error", "Invalid value read error: " + x);
        }
        return topicList;
    }

    private ArrayList<Picture> getPictureFromSheet(Sheet sheet) {
        Drawing draw = sheet.createDrawingPatriarch();
        ArrayList<Picture> pics = new ArrayList<>();
        if (draw instanceof HSSFPatriarch) {
            HSSFPatriarch hp = (HSSFPatriarch)draw;
            for (HSSFShape hs : hp.getChildren()) {
                if (hs instanceof Picture)  {
                    pics.add((Picture)hs);
                }
            }
        } else {
            XSSFDrawing xdraw = (XSSFDrawing)draw;
            for (XSSFShape xs : xdraw.getShapes()) {
                if (xs instanceof Picture) {
                    pics.add((Picture)xs);
                }
            }
        }
        return pics;
    }

    private Integer getTopicImage(String topicName) {
        switch (topicName) {
            case "Education":
                return R.drawable.ic_education;
            case "Society":
                return R.drawable.ic_society;
            case "Business":
                return R.drawable.ic_business;
            case "Technology":
                return R.drawable.ic_technology;
            case "Space":
                return R.drawable.ic_space;
            case "Animal":
                return R.drawable.ic_animal;
            case "Plant":
                return R.drawable.ic_plant;
            case "Art":
                return R.drawable.ic_art;
            case "Sport":
                return R.drawable.ic_sport;
            default:
                return R.drawable.ic_nature;
        }
    }
}