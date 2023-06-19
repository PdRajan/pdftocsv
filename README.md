# pdftocsv
pdftocsv is a program to generate results from the result PDFs released by GGSIP University.

## Command
java com.PDFtoCSV.PDFReader [input path] [output path] [instution code] [semester] [new/old] 

1. **Input path** : Enter the path of the input PDF file. Use quotes if there are spaces in the filename or folder name.
2. **Output path** : Enter the path of the output CSV file. Use quotes if there are spaces in the filename or folder name.
3. **Instustion** : Enter the institution code for your college.
4. **Semester** : Enter the semester.
5. **Format** : Specify the format of PDF, new or old.

## Example Command
java com.PDFtoCSV.PDFReader ~/pdf/bca3.pdf bca3.csv 149 3 new 



## Original authors:
raman.batra1709@gmail.com\
Neeraj
