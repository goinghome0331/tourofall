package net.bulldozer.tourofall;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bulldozer.tourofall.dest.dto.BestDestinationRenderingModel;
import net.bulldozer.tourofall.dest.service.TodayDestinationService;

@SpringBootApplication
@Controller
public class App {
	
	public static void main(String args[]) {
		SpringApplication.run(App.class,args);
	}
	
	@Autowired
	private TodayDestinationService todayDestinationService;  
	
	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("todayDestinationRenderingModels", todayDestinationService.getTodayDestinationRenderingModels());
		List<BestDestinationRenderingModel> bestDestinationRenderingModels = new ArrayList<BestDestinationRenderingModel>();
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("경주 동궁과 월지",128526,"http://tong.visitkorea.or.kr/cms/resource/44/2367744_image2_1.jpg","신라 왕궁의 별궁터"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("하늘물빛정원",2022638,"http://tong.visitkorea.or.kr/cms/resource/25/2022625_image2_1.jpg","머들령 계곡을 흐르는 장산호수"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("섬진강기차마을",128578,"http://tong.visitkorea.or.kr/cms/resource/89/2032689_image2_1.jpg","고달면 가정리가 곡성군 관광명소"));
		bestDestinationRenderingModels.add(new BestDestinationRenderingModel("프로방스 마을",129196,"http://tong.visitkorea.or.kr/cms/resource/01/1998801_image2_1.jpg","프로방스 리빙관, 허브관, 패션관, 카페로 구성되어진 테마형 마을"));
		model.addAttribute("bestDestinationRenderingModels", bestDestinationRenderingModels);
		return "home";
	}
}
