package projectPSC.resources;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;

public class ExcelSheet {

    private static final String excel = "C:\\Users\\Správca\\Desktop\\PSCobci.xlsx";

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectpsc", "root", "");
            String delete= "DELETE FROM psc";
            String query = "INSERT INTO psc(City,Country,PSC)VALUES(?,?,?)";
            PreparedStatement clear = conn.prepareStatement(delete);
            clear.executeUpdate();
            PreparedStatement ps = conn.prepareStatement(query);


            FileInputStream excelFile = new FileInputStream(new File(excel));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int rowIndex=1;
            String City;
            String Country;
            String PSC;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                if(rowIndex==1)
                {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext())
                {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getColumnIndex()==0)
                    {
                        City=currentCell.getStringCellValue();
                        ps.setString(1,City);
                        System.out.println(City);
                    }
                    else  if (currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getColumnIndex()==1)
                    {
                        Country=currentCell.getStringCellValue();
                        ps.setString(2,Country);
                        System.out.println(Country);
                    }

                    else if (currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getColumnIndex()==2)
                    {
                        PSC=currentCell.getStringCellValue();
                        ps.setString(3,PSC);
                        System.out.println(PSC);
                    }
                }

                ps.executeUpdate();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}