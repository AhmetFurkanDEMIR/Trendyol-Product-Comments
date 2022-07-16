![](https://camo.githubusercontent.com/a85162d7bbc861f8f5e7e8b127ff5162e348065ae0eb91256a1efc1919f8f1ba/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4f70656e4a444b2d4544384230303f7374796c653d666f722d7468652d6261646765266c6f676f3d6f70656e6a646b266c6f676f436f6c6f723d7768697465) ![](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) ![](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white) ![](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)

## Trendyol-Product-Comments 

![index](https://user-images.githubusercontent.com/54184905/179355852-c5f019d9-cf9b-4104-81f5-8fb645ea4c86.jpeg)

Web scraping tool written in Java Selenium and Jsoup for pulling product and product reviews from Trendyol e-commerce site.


**Kaggle Dataset :** https://www.kaggle.com/datasets/ahmetfurkandemr/trendyol-product-comments

**Kaggle Comment classification project :** 


* **Gender of clothing products.**

```java
/*
1 : Women
2 : Men
3 : Child
*/

String gender = "1,2,3";
```


* **Brands of clothing products**

```java
String brands = "38,37,271,101990,43,44,136,230,33,124,160,101439,436,189,257,146279,131,634,150,859";

// Products
String url = "https://www.trendyol.com/sr?wg="+genders+"&wb="+brands+"&wc=82&os=1&pi=";
```


* **The schema of the tables created for writing the data**

![Screenshot_2022-07-16_12-51-17](https://user-images.githubusercontent.com/54184905/179349931-1653f13a-dec4-45a9-b7eb-892672a99bba.png)

