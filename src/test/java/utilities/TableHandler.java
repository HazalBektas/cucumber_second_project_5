package utilities;

import co.boorse.seleniumtable.SeleniumTable;
import co.boorse.seleniumtable.SeleniumTableCell;
import co.boorse.seleniumtable.SeleniumTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableHandler {
   static SeleniumTable seleniumTable;


    public static List<SeleniumTableRow> getRows(WebElement table) {
        seleniumTable =SeleniumTable.getInstance(table);
        return  seleniumTable.rows();


    }

    public static SeleniumTableRow getRow(WebElement table, int row){
        return getRows(table).get(row);
    }

    public  static List<SeleniumTableCell> getCells(WebElement table, int row){
        return getRow(table,row).cells();


}
public static SeleniumTableCell getCell(WebElement table, int row, int cell){
        return getCells(table, row).get(cell);

    }

    public  static WebElement getCheckBox(WebElement table, int row, int cell){
        return getCell(table, row, cell).getElement().findElement(By.tagName("input"));
    }
}
