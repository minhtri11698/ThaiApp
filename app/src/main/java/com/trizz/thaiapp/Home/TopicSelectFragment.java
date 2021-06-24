package com.trizz.thaiapp.Home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class TopicSelectFragment extends Fragment {

    public TopicSelectFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_list_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView topicRecycler = requireActivity().findViewById(R.id.topic_recycler_view);
        ArrayList<TopicModel> topicList = readExcel(requireContext());
        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(topicList, item -> ((HomeActivity) requireActivity()).showFragment(TopicFragment.newInstance(item), true, R.id.container));
        topicRecycler.setAdapter(adapter);
        topicRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 3));
    }

    public static TopicSelectFragment newInstance() {
        TopicSelectFragment fragment = new TopicSelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /** read excel data file to get all the data
     * Duyet file excel theo hang va cot theo tung sheet
     * File excel duoc luu trong folder assets voi cau truc la cot 1 la tu, cot 2 la nghia va cot 3 la hinh */

    private ArrayList<TopicModel> readExcel(Context context) {
        ArrayList<TopicModel> topicList = new ArrayList<>();
        try {
            // Tao mot inputStream den duong dan cua file duoc luu
            InputStream is = context.getApplicationContext().getAssets().open("data.xlsx");
            Workbook workbook = WorkbookFactory.create(is);
            int numberOfSheet = workbook.getNumberOfSheets(); // So luong sheet trong file
            workbook.getAllPictures();
            int sheetIndex = 0;
            while (sheetIndex < numberOfSheet) { // Vong lap cac sheet cua file tu sheet 0. Moi sheet la mot topic tu
                ArrayList<WordModel> wordList = new ArrayList<>(); // List cac tu trong sheet
                Sheet sheet = workbook.getSheetAt(sheetIndex); // Lay ra sheet theo index
                sheetIndex++;
                ArrayList<Picture> pictureArrayList = getPictureFromSheet(sheet); // Lay ra list anh tu sheet
                int index = 0;
                for (Row row : sheet) { // Duyet file theo cac hang
                    String wordText = row.getCell(0).getStringCellValue(); // La ra word duoc luu o cot 0
                    String meaning = row.getCell(1).getStringCellValue(); // Lay ra meaning duoc luu o cot 1
                    Picture pic = index < pictureArrayList.size() ? pictureArrayList.get(index) : null; // Lay anh cua hinh theo thu tu index. Cach nay co the lay ra hinh sai neu co 1 tu khong co hinh anh
                    index++;
                    WordModel word = new WordModel(wordText, meaning, pic); // Tao mot object word de luu lai va add vao list tu
                    wordList.add(word);
                }
                TopicModel topic = new TopicModel(getTopicImage(sheet.getSheetName()), sheet.getSheetName(), wordList); // Sau khi duyet het sheet la xong 1 topic. Luu lai
                topicList.add(topic);
            }
        } catch (IOException e) {
            Log.d("Error", "Read excel error: " + e);
        } catch (InvalidFormatException x) {
            Log.d("Error", "Invalid value read error: " + x);
        }
        return topicList;
    }

    /** get all the image from a sheet, return list of image
     * Lay ra anh duoi dang Object Picture cua ApachePOI */
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
