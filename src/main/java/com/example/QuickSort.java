package com.example;

class  QuickSort
{
	static int i;
	static int j;
	static Student temp;

	public static void ascSort(Student[] a)
	{
		ascSortRecur(a, 0, a.length-1);
	}

	public static void ascSortRecur(Student[] a, int s, int e)
	{
		if(s>=e)
			return ;
		i=s;
		j=e-1;
		while(j>=i){
			while(a[i].getStrictness()<a[e].getStrictness())
				i++;
			while(j>i&&a[j].getStrictness()>a[e].getStrictness())
				j--;
			if(j>=i)
				swap(a, i++, j--);
		}
		swap(a, ++j, e);

		ascSortRecur(a, s, j-1);
		ascSortRecur(a, j+1, e);
	}

	public static void desSort(Student[] a)
	{
		desSortRecur(a, 0, a.length-1);
	}

	public static void desSortRecur(Student[] a, int s, int e)
	{
		i=s;
		j=e-1;
		while(j>=i){
			/*
			if(a[i]<a[e])
				i++;
			else if(a[j]>a[e])
				j--;
			else
				swap(a, i++, j--);
				*/
			while(a[i].getStrictness()>a[e].getStrictness())
				i++;
			while(j>i&&a[j].getStrictness()<a[e].getStrictness())
				j--;
			if(j>=i)
				swap(a, i++, j--);
		}
		swap(a, ++j, e);
		if(j-s>1)
			desSortRecur(a, s, j-1);
		if(e-j>1)
			desSortRecur(a, j+1, e);
	}

	public static void swap(Student[] a, int i, int j)
	{
		temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
