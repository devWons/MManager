package si.cc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Mmain {
	
	/*
	 * 입력된 제외수 중복값 제거 및 정렬 후 Return.
	 * */
	public static List<Integer> ExceptNumber(String[] iNum) {
		Set<Integer> shInt = new HashSet<Integer>();
		List<Integer> eNumList = null;
		
		for(int i=0; i<iNum.length; i++)
		{
			shInt.add(Integer.parseInt(iNum[i]));
		}
		
		eNumList = new LinkedList<Integer>(shInt);
		Collections.sort(eNumList);
		
		System.out.println("-------------------------제외수 목록-------------------------");
		System.out.println(eNumList.toString());
		System.out.println("\n\n-------------------------제외수 목록-------------------------");
		return eNumList;
	}
	
	/*
	 * 생성된 번호가 제외수인지 확인.
	 * */
	public static Boolean ExceptCheck(int num, List<Integer> nExList)
	{
		Boolean rtn = false;
		for(int i=0; i<nExList.size(); i++)
		{
			if(nExList.get(i) == num)
			{
				rtn = true;
				break;
			}
		}
		return rtn;
	}
	
	/*
	 * 로또번호 생성
	 * */
	public static List<Integer> createLottoNumber(List<Integer> nExList)
	{
		Random ran = new Random();
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> list = null;
		while (set.size() < 6) { //셋에서는 이렇게 6자리의 숫자가 정해질때까지 추출하면 됨.
            int num2 = ran.nextInt(45) + 1;
            if(!ExceptCheck(num2, nExList))
            {
            	set.add(num2);
            }
        }
        //숫자 정렬
        list = new LinkedList<Integer>(set);
        Collections.sort(list);
//        System.out.println("\n-------로또번호 생성---------");
//        System.out.println(list.toString());
//        System.out.println("-------로또번호 생성---------");
        return list;
	}
	
	/*
	 * 번호의 합이 예상 당첨 구간인지 확인
	 * */
	public static Boolean SectionCompare(List<Integer> lNum, int sMin, int sMax) {
		int sum = 0;
		for(int i=0; i<lNum.size(); i++)
		{
			sum += lNum.get(i);
		}
		
		if(sum >= sMin && sum <= sMax)
		{
//			System.out.println("예상번호의 합 : ["+ sum +"], 최소값 : ["+sMin+"], 최대값: ["+sMax+"]. true.");
			return true; 
		} else {
//			System.out.println("예상번호의 합 : ["+ sum +"], 최소값 : ["+sMin+"], 최대값: ["+sMax+"]. false.");
			return false;
		}
	}

	public static void main(String[] args) {
		/**********************************************
		 * SETTING.
		 **********************************************/
		//몇 게임 구매할 것인가.
		int game = 5;
		
		/**********************************************
		 * 1. 제외할 수를 입력하세요.
		 **********************************************/
		String[] exNum = {
				"1","2","3","10","11","12",
				"23","24","35","36","17","29"
				};
		List<Integer> nExList = ExceptNumber(exNum);
		
		/**********************************************
		 * 2. 예상 당첨번호 생성
		 * 3. 번호의 합이 예상 당첨 구간인지 확인
		 **********************************************/
		int sMin = 130;
		int sMax = 140;
		List<Integer> lNum = null;
		ArrayList<String> arrNumList = new ArrayList<>();
		Boolean exit = true;
		int cnt = 0;
		while (exit) {
			//예상 당첨번호 생성
			lNum = new LinkedList<Integer>();
			lNum = createLottoNumber(nExList);

			//구간확인
			if(SectionCompare(lNum, sMin, sMax))
			{
		        System.out.println((cnt+1) +"생성번호 : "+lNum.toString());
				arrNumList.add(lNum.toString());
				cnt++;
				
				if(cnt == game)
				{
					exit = false;//당첨구간이라면 동작그만.
				}
			}
		}
		// 6 10 16 28 34 38
		List<Integer> win = new LinkedList<Integer>();
		win.add(6);
		win.add(10);
		win.add(16);
		win.add(28);
		win.add(34);
		win.add(38);
		
		
        /**********************************************
		 * ?. 기존 당첨번호는 제외
		 **********************************************/
		
        
        
        System.out.println("\n-------------------------로또 예상 번호-------------------------");
        System.out.println(arrNumList.toString());
        System.out.println("-------------------------로또 예상 번호-------------------------");
	}

}
