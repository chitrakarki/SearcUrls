package com.exam.SearchUrls;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FindUrls findUrls = new FindUrls();
		findUrls.FindUrlsRecursively("https://www.linkedin.com/");
		
		findUrls.getAllUrls().forEach(System.out::println);		
		findUrls.getAllErrors().forEach(System.out::println);
    }
}
