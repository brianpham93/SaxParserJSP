package thangpq;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class XmlSaxParser extends DefaultHandler {

    private List<Employee> employees;
    private Employee employee;    
    private String tagName;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(tagName != null){
            String data = new String(ch,start,length);
            switch (this.tagName) {
                case "HoTen":
                    this.employee.setName(data);
                    break;
                case "MaPhong":
                    this.employee.setDepartment(data);
                    break;
                case "Luong":
                    this.employee.setSalary(Double.parseDouble(data));
                    break;
                case "Thuong":
                    this.employee.setBonus(Double.parseDouble(data));
                    break;
            }            
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("NhanVien")){
            this.employees.add(this.employee);
        }
        this.tagName = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("NhanVien")){
            employee = new Employee();
        }
        this.tagName = qName;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startDocument() throws SAXException {
        employees = new ArrayList<Employee>();
        
    }
        
}
