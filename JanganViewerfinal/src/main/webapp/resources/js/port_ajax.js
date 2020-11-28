	$(document).ready(function() {

		$('.list_ip').hide();
		$('.list_ip').each(function() {
			
			var all_length = $('.list_ip').length;	
			
			var ip_num = $(this).text();
			$.ajax({
				url : "./portScanTest.do", 
				type : "GET",
				data : {
					"ip_num" : ip_num
				},
				success : function(res) {
					var target = document.getElementById('target');
					var div1 = document.createElement('div');
					var div2 = document.createElement('div');
					var ul3 = document.createElement('ul');
					var li4_1 = document.createElement('li');
					var li4_2 = document.createElement('li');
					var li4_3 = document.createElement('li');
					var li4_4 = document.createElement('li');

					var text1 = document.createTextNode('포트');
					var text2 = document.createTextNode('상태');
					var text3 = document.createTextNode('서비스명');
					var text4 = document.createTextNode('ip주소');
					var jsonOBj = JSON.parse(res);
					var array = jsonOBj.port;
					var i = 0;
					
					$(div1).addClass('wrapper');
					$(div2).addClass('row title');
					li4_1.appendChild(text1);
					li4_2.appendChild(text2);
					li4_3.appendChild(text3);
					li4_4.appendChild(text4);

					ul3.appendChild(li4_1);
					ul3.appendChild(li4_2);
					ul3.appendChild(li4_3);
					ul3.appendChild(li4_4);
					
					div2.appendChild(ul3);
					div1.appendChild(div2);
					
					for (idx in array) {
						var text1content = document.createTextNode(array[idx].port_num);
						var text2content = document.createTextNode(array[idx].port_state);
						var text3content = document.createTextNode(array[idx].port_service);
						var text4content = document.createTextNode(jsonOBj.search_ip);
						var text5content = document.createTextNode(array[idx].port_description);
						var textDescript = document.createTextNode('설명: ');
						var text6content = document.createTextNode(' 취약점 분석');
						
						var a5_1content = document.createElement('a');
						var a5_2content = document.createElement('a');
						
						var li4_1content = document.createElement('li');
						var li4_2content = document.createElement('li');
						var li4_3content = document.createElement('li');
						var li4_4content = document.createElement('li');
						
						var liDescript = document.createElement('li');
						
						var ul3Descript = document.createElement('ul');
						
						var ul3content = document.createElement('ul');
						var div2content = document.createElement('div');
						
						a5_2content.href = 'https://cve.mitre.org/cgi-bin/cvekey.cgi?keyword='+array[idx].port_service;
						
						a5_1content.appendChild(text1content);
						a5_2content.appendChild(text6content);
						
						li4_1content.appendChild(a5_1content);
						li4_2content.appendChild(text2content);
						li4_3content.appendChild(text3content);
						li4_4content.appendChild(text4content);
						
						ul3content.appendChild(li4_1content);
						ul3content.appendChild(li4_2content);
						ul3content.appendChild(li4_3content);
						ul3content.appendChild(li4_4content);
						
						liDescript.appendChild(textDescript);
						liDescript.appendChild(text5content);
						liDescript.appendChild(a5_2content);
						
						$(ul3Descript).addClass('more-content');
						ul3Descript.appendChild(liDescript);
						div2content.appendChild(ul3content);
						div2content.appendChild(ul3Descript);
						
						if( i==0 ){
							$(div2content).removeClass();
							$(div2content).addClass('row nfl row-fadeIn-wrapper');
							i = i + 1;
						}else if( i==1 ){
							$(div2content).removeClass();
							$(div2content).addClass('row mlb row-fadeIn-wrapper');
							i = i + 1;
						}else if( i==2 ){
							$(div2content).removeClass();
							$(div2content).addClass('row nhl row-fadeIn-wrapper');
							i = i + 1;
						}else{
							$(div2content).removeClass();
							$(div2content).addClass('row pga row-fadeIn-wrapper');
							i = 0;
						}
						div1.appendChild(div2content);
					}
							
					target.appendChild(div1);
					var compare_length = Number($('#compare').val()) + Number(1);
					var percent = compare_length / all_length * 100;
					var percent_result = percent.toFixed(1);
					$(".test2").text(percent_result+'% 진행중');
					$('#compare').val(Number(compare_length));
					
					if(all_length == compare_length){
						$(".test2").text('완료');
						$('.loading-container').fadeOut(5000);
						
					}
						
					//var jsonStr = JSON.stringify(res);
					//var jsonOBj = JSON.parse(jsonStr);// 안됨.
				}
			})

		});

	});