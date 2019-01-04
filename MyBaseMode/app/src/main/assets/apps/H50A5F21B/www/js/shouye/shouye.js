alert(1);
mui.plusReady(function(){
	mui.init();
	// plus.nativeUI.showWaiting('正在加载中...');
	ca.init();
	if(window.screen.height<660){
		ca.id('body').style.marginBottom=660-window.screen.height;
	}

	
// 	document.addEventListener("plusscrollbottom", onScrollToBottom, false);
// 	function onScrollToBottom(){
// 	// 处理滚动到窗口底部事件
// 		// alert('到底了，加载数据');
// 		var uls=ca.id('recommendList');
// 		var li=ca.className('h120')[0].innerHTML;
// 		for(var i=0;i<5;i++){
// 			uls.innerHTML+=li;
// 		}
// 		
// 	}
	var network=plus.networkinfo.getCurrentType();
	if(network==1 ||network==4){
// 		plus.nativeUI.showWaiting('正在请求数据')
// 		mui.toast('请检查网络环境');
	}else{
		if(!ca.emptyFun(plus.storage.getItem('indexData'))){//判断是否存储有数据
			showAll();//直接显示
		}else{
			findAll1();
		}
	}
	document.addEventListener("netchange",checkTheNetwork,false);//检测网络环境发生变化
	function checkTheNetwork(){
		ca.getMachineInfo(function(data){
			if(data.network=='未连接网络'){
				mui.toast('网络断开连接');
			}else if(data.network=='2G蜂窝网络' || data.network=='3G蜂窝网络'){
				mui.toast('网络断开连接')
			}else{
				showAll();
				setRecommendLine();
			}
		})
	}
	
	function showAll(){
		var data=JSON.parse(plus.storage.getItem('indexData'));
		if(!ca.emptyFun(data.msg)&&data.msg==1){
			if(!ca.emptyFun(data.indexBannerArr)){
				handleBanner(data.indexBannerArr);
			}
			if(!ca.emptyFun(data.indexLineArr)){
				handleLine(data.indexLineArr);
			}
			if(!ca.emptyFun(data.indexTopXiArr)){
				var indexTopReArr=data.indexTopXiArr.sort(ca.getSortFun('desc','hits'));
				handleTop(indexTopReArr,data.indexTopXiArr);
				hui.scrollNews(scrollnew1);
			}
			if(!ca.emptyFun(data.indexTicketArr)){
				handleScenicSpot(data.indexTicketArr);
			}
			findAll();//再次更新存储内容
		}else{
			findAll1();
		}
	}
	
	function findAll(){
		ca.ajaxs({url:'findIndexAdvertising',data:{
			},succFn:function(data){
				if(data){
					plus.storage.setItem('indexData',JSON.stringify(data));
				}
			},errFn:function(err){
				ca.closeWaiting();
				mui.toast(err);
			}
		})
	}
	function findAll1(){
		ca.ajaxs({url:'findIndexAdvertising',data:{
			},succFn:function(data){
				if(data){
					plus.storage.setItem('indexData',JSON.stringify(data));
					showAll();
				}
			},errFn:function(err){
				ca.closeWaiting();
				mui.toast(err);
			}
		})
	}
	function handleBanner(indexBannerArr){
		var indexBannerArr=indexBannerArr;
		var indexBannerArrinnerHTML="";
		var dianhtml="";
		for(var a in indexBannerArr){
			var length=indexBannerArr.length-1;
			var temp="";
			var dian="";
			if(a==0){
				dian='<div class="mui-indicator mui-active" ></div>';
				temp='<div class="mui-slider-item mui-slider-item-duplicate" data_id="'+indexBannerArr[length].id+'" data_type="'+indexBannerArr[length].type+'" data_product_type="'+indexBannerArr[length].product_type+'" data_item_id="'+indexBannerArr[length].item_id+'" data_product="'+(indexBannerArr[length].product?indexBannerArr[length].product.id:0)+'"><a href="#"><img src="'+indexBannerArr[length].pic+'" /></a></div>'
					+'<div class="mui-slider-item" data_id="'+indexBannerArr[a].id+'" data_type="'+indexBannerArr[a].type+'" data_product_type="'+indexBannerArr[a].product_type+'" data_item_id="'+indexBannerArr[a].item_id+'" data_product="'+(indexBannerArr[a].product?indexBannerArr[a].product.id:0)+'"><a href="#"><img src="'+indexBannerArr[a].pic+'" /></a></div>';
			}else if(a==length){
				dian='<div class="mui-indicator" ></div>';
				temp='<div class="mui-slider-item" data_id="'+indexBannerArr[a].id+'" data_type="'+indexBannerArr[a].type+'" data_product_type="'+indexBannerArr[a].product_type+'" data_item_id="'+indexBannerArr[a].item_id+'" data_product="'+(indexBannerArr[a].product?indexBannerArr[a].product.id:0)+'"><a href="#"><img src="'+indexBannerArr[a].pic+'" /></a></div>'
				+'<div class="mui-slider-item mui-slider-item-duplicate" data_id="'+indexBannerArr[0].id+'" data_type="'+indexBannerArr[0].type+'" data_product_type="'+indexBannerArr[0].product_type+'" data_item_id="'+indexBannerArr[0].item_id+'" data_product="'+(indexBannerArr[0].product?indexBannerArr[0].product.id:0)+'"><a href="#"><img src="'+indexBannerArr[0].pic+'" /></a></div>';
			}else if(a!=0 && a<length){
				dian='<div class="mui-indicator" ></div>';
				temp='<div class="mui-slider-item" data_id="'+indexBannerArr[a].id+'" data_type="'+indexBannerArr[a].type+'" data_product_type="'+indexBannerArr[a].product_type+'" data_item_id="'+indexBannerArr[a].item_id+'" data_product="'+(indexBannerArr[a].product?indexBannerArr[a].product.id:0)+'"><a href="#"><img src="'+indexBannerArr[a].pic+'"/></a></div>';
			}
			indexBannerArrinnerHTML+=temp;
			dianhtml+=dian;
		}
		ca.id('bannerdian').innerHTML=dianhtml;
		ca.id('banners').innerHTML=indexBannerArrinnerHTML;
		ca.pictureScroll({
			callback:function(pictureNumber){
			},
			isAutoScroll:true,
			scrollTime:3
		});
	}
	function handleLine(indexLineArr){
			try{
				ca.id('category-head').innerHTML=template('lineitem', {
					"list":indexLineArr
				});
			}catch(err){
				console.log(err);
			}
	}
	function handleTop(re,xi){
		var indexBannerArrinnerHTML="";
		mui.each(re,function(index,item){
			var temp='<div class="hui-scroll-news-items oh" >'
					+		'<div style="float:left;width: 80%;">'
					+				'<div class="mt5 handleTop oh" data_id="'+xi[index].id+'" data_type="'+xi[index].type+'" data_product_type="'+xi[index].product_type+'" data_item_id="'+xi[index].item_id+'" data_product="'+(xi[index].product?xi[index].product.id:0)+'"><span class="index-div3-div2-tt-sp1">最新</span>'
					+				'<span class="fs12 c333333 ml3">'+xi[index].title+'</span></div>'
					+				'<div class="mt3 handleTop" data_id="'+re[index].id+'" data_type="'+re[index].type+'" data_product_type="'+re[index].product_type+'" data_item_id="'+re[index].item_id+'" data_product="'+(re[index].product?re[index].product.id:0)+'"><span class="index-div3-div2-tt-sp1">热推</span>'
					+				'<span class="fs12 c333333 ml3">'+re[index].title+'</span></div>'
					+		'</div>'
					+		'<div class="handleTop" style="width: 20%;" data_id="'+xi[index].id+'" data_type="'+xi[index].type+'" data_product_type="'+xi[index].product_type+'" data_item_id="'+xi[index].item_id+'" data_product="'+(xi[index].product?xi[index].product.id:0)+'"><img src="'+xi[index].pic+'" style="width: 100%;height: 100%;"/></div>'	
					+'</div>';
			ca.id('scrollnew1').innerHTML+=temp;
		})
	}
	function handleScenicSpot(data){
		var a=1,b=1,c=1,d=1;
		ca.id('index-div5-div2-d1').innerHTML="";
		ca.id('index-div5-div2-d2').innerHTML="";
		ca.id('index-div5-div2-d3').innerHTML="";
		ca.id('index-div5-div2-d4').innerHTML="";
		if(!ca.emptyFun(data.ticketArr1)){
			mui.each(data.ticketArr1,function(index,item){
				var temp="";
				if(a%3==0){
					temp='<li style="margin-right:0%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}else{
					temp='<li style="margin-right:2%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}
				ca.id('index-div5-div2-d1').innerHTML+=temp;
				a=a+1;
			})
		}else{
			ca.id('index-div5-div2-d1').innerHTML='<li style="width: 100%;text-align: center;margin-top:15px;"><span class="fs12 c999999">暂无内容</span></li>';
		}
		if(!ca.emptyFun(data.ticketArr2)){
			mui.each(data.ticketArr2,function(index,item){
				var temp="";
				if(b%3==0){
					temp='<li style="margin-right:0%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}else{
					temp='<li style="margin-right:2%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}
				ca.id('index-div5-div2-d2').innerHTML+=temp;
				b=b+1;
				
			})
		}else{
			ca.id('index-div5-div2-d2').innerHTML='<li style="width: 100%;text-align: center;margin-top:15px;"><span class="fs12 c999999">暂无内容</span></li>';
		}
		if(!ca.emptyFun(data.ticketArr3)){
			mui.each(data.ticketArr3,function(index,item){
				var temp="";
				if(c%3==0){
					temp='<li style="margin-right:0%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}else{
					temp='<li style="margin-right:2%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}
				ca.id('index-div5-div2-d3').innerHTML+=temp;
				c=c+1;
				
			})
		}else{
			ca.id('index-div5-div2-d3').innerHTML='<li style="width: 100%;text-align: center;margin-top:15px;"><span class="fs12 c999999">暂无内容</span></li>';
		}
		if(!ca.emptyFun(data.ticketArr4)){
			mui.each(data.ticketArr4,function(index,item){
				var temp="";
				if(d%3==0){
					temp='<li style="margin-right:0%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}else{
					temp='<li style="margin-right:2%;" data_id="'+item.id+'" data_type="'+item.type+'" data_product_type="'+item.product_type+'" data_item_id="'+item.item_id+'" data_product="'+(item.product?item.product.id:0)+'" class="handleScenicSpot"><img src="'+item.pic+'" /></li>';
				}
				ca.id('index-div5-div2-d4').innerHTML+=temp;
				d=d+1;
				
			})
		}else{
			ca.id('index-div5-div2-d4').innerHTML='<li style="width: 100%;text-align: center;margin-top:15px;"><span class="fs12 c999999">暂无内容</span></li>';
		}
	}
	
	var tables=ca.className('scenic_spot');
	for(var i=0;i<tables.length;i++){
		(function(index){
			tables[i].addEventListener('tap',function(){
				for(var a=0;a<tables.length;a++){
					if(index==a){
						tables[index].children[0].classList.remove('scenic_spot-d1');
						tables[index].children[0].classList.add('scenic_spot-d1-active');
						tables[index].children[1].classList.add('scenic_spot-d2-active');
						switchScenicSpot(index);
					}else{
						tables[a].children[0].classList.remove('scenic_spot-d1-active');
						tables[a].children[1].classList.remove('scenic_spot-d2-active');
						tables[a].children[0].classList.add('scenic_spot-d1');
					}
				}
			})
		})(i);
	}
	setRecommendLine();
	function setRecommendLine(){
		var recommendLine=JSON.parse(localStorage.getItem('RandomRecommendLine'));
		if(!ca.emptyFun(recommendLine)){
			try{
				ca.id('recommendList').innerHTML=template('RecommendLine', {
					"list":recommendLine
				});
			}catch(e){
				console.log(e);
			}
		}else{
			findRecommendLine();
		}
	}
	findRecommendLine();
	function findRecommendLine(){
		ca.ajaxs({url:'findRandomRecommendLine',data:{
			},succFn:function(data){
				if(data){
					try{
						ca.id('recommendList').innerHTML=template('RecommendLine', {
							"list":data
						});
					}catch(e){
						console.log(e);
					}
					localStorage.setItem('RandomRecommendLine',JSON.stringify(data));
				}
				ca.closeWaiting();
			},errFn:function(err){
				ca.closeWaiting();
				mui.toast(err);
			}
		})
	}
	mui('#recommendList').on('tap','.h120',function(){
		ca.showWaiting();
		var id=this.getAttribute('data_id');
		var pl=plus.webview.create(
			'line/line_listitem_detail.html',
			'line_listitem_detail',{},{lineid:id})
		setTimeout(function(){
			pl.show('slide-in-right',400);
		},300)
		// ca.newInterface({url:'line_listitem_detail.html',id:''});
	})
	
	function switchScenicSpot(index){
		var cell=ca.className('index-div5-div2-d1');
		for(var i=0;i<cell.length;i++){
			if(i==index){
				cell[index].classList.remove('disn');
				cell[index].classList.add('disb');
			}else{
				cell[i].classList.remove('disb');
				cell[i].classList.add('disn');
			}
		}
	}
	ca.id('search').addEventListener('tap',function(){
		ca.newInterface({url:'search.html',id:'search'});
	});
	ca.id('tongzhi').addEventListener('tap',function(){
		ca.showWaiting();
		var self=plus.webview.create('information.html','information',{},{});
		setTimeout(function(){
			self.show('slide-in-right',400);
			// pop-in
		},200)
		// ca.newInterface({url:'information.html',id:'information'});
	})
	var cell=ca.className('w20');
// 				cell[4].addEventListener('tap',function(){
// // 					plus.webview.open('http://m.weibo.cn/u/3196963860', 'test', {popGesture:'close',backButtonAutoControl:'close',statusbar:{background:'#FFFFFF'},titleNView:{autoBackButton:'true',backgroundColor:'#FFFFFF',progress:{color:'#999999'},buttons:[{type:'menu',float:'right',onclick:clickButton}]
// // 					}});
// 					ca.newInterface({url:'Test/new_file.html',id:'new_file'});
// 					
// 				})
	cell[0].addEventListener('tap',function(){
		ca.showWaiting();
		var self=plus.webview.create('line/line_tourist_route_list.html','line_tourist_route_list',{},{type:1});
		setTimeout(function(){
			self.show('slide-in-right',400);
			// pop-in
		},200)
	})
	cell[1].addEventListener('tap',function(){
		ca.showWaiting();
		var self=plus.webview.create('line/line_tourist_route_list.html','line_tourist_route_list',{},{type:2});
		setTimeout(function(){
			self.show('slide-in-right',400);
			// pop-in
		},200)
	})
	cell[2].addEventListener('tap',function(){
		ca.showWaiting();
		var self=plus.webview.create('line/line_tourist_route_list.html','line_tourist_route_list',{},{type:3});
		setTimeout(function(){
			self.show('slide-in-right',400);
		},200)
	})
	cell[3].addEventListener('tap',function(){
		ca.showWaiting();
		var self=plus.webview.create('visa/visa_index.html','visa_index',{},{});
		setTimeout(function(){
			self.show('slide-in-right',400);
			// pop-in
		},200)
		
	})
	cell[4].addEventListener('tap',function(){
		// ca.newInterface({url:'Test/testkeyboard.html',id:'testkeyboard'});
// 					castapp.share({
// 						shareTitle:'这里是标题',
// 						shareContent: '这里是内容',
// 						shareImg:'http://www.castapp/logo.png',
// 						shareLink:'http://www.castapp.cn'
// 					});
// 					var sty={statusbar:{background:'#FFFFFF'},popGesture:'close',progress:{},titleNView:{autoBackButton:true,backgroundColor:'#FFFFFF',progress:{},tags:[{tag:'rect',id:'rect',rectStyles:{color:'#FF0000'},position:{top:'8px',left:'15%',right:'15%',width:'70%',height:'28px'},rectStyles:{color:'#999999',radius:'7px',borderColor:'#000000',borderWidth:'1px'}},{tag:'font',id:'font',text:'搜索车队',rectStyles:{color:'#FF0000'}}]}}
// 					var self=plus.webview.create('Test/titleNView.html','titleNView',sty);
// 					self.show();
	})
	cell[5].addEventListener('tap',function(){
		ca.newInterface({url:'ticket/train_tickets_top.html',id:'train_tickets_top'});
	})
	cell[6].addEventListener('tap',function(){
		ca.newInterface({url:'ticket/plane_ticket.html',id:'plane_ticket'});
	})
	cell[7].addEventListener('tap',function(){
		ca.newInterface({url:'ticket/car_ticket.html',id:'car_ticket'});
	})
	cell[8].addEventListener('tap',function(){
		mui.toast('暂无更多!');
	})
	
	
	// https://m.ly.com/bus/
	function clickButton(){
		plus.nativeUI.actionSheet({title:"菜单",cancel:"取消",buttons:[{title:"1"},{title:"2"}]}, 
			function(e){
		})
	}
	
	mui('#slider').on('tap','.mui-slider-item',function(){
		var id=this.getAttribute('data_id');
		var type=this.getAttribute('data_type');
		var item_id=this.getAttribute('data_item_id');
		var product_type=this.getAttribute('data_product_type');
		var product=this.getAttribute('data_product');
		onclickAdvertisement(type,product_type,product,item_id,id);
	});
	mui('#scrollnew1').on('tap','.handleTop',function(){
		var id=this.getAttribute('data_id');
		var type=this.getAttribute('data_type');
		var item_id=this.getAttribute('data_item_id');
		var product_type=this.getAttribute('data_product_type');
		var product=this.getAttribute('data_product');
		onclickAdvertisement(type,product_type,product,item_id,id);
	})
	mui('#category-head').on('tap','.handleLine',function(){
		var id=this.getAttribute('data_id');
		var type=this.getAttribute('data_type');
		var item_id=this.getAttribute('data_item_id');
		var product_type=this.getAttribute('data_product_type');
		var product=this.getAttribute('data_product');
		onclickAdvertisement(type,product_type,product,item_id,id);
	})
	mui('#index-div5').on('tap','.handleScenicSpot',function(){
		var id=this.getAttribute('data_id');
		var type=this.getAttribute('data_type');
		var item_id=this.getAttribute('data_item_id');
		var product_type=this.getAttribute('data_product_type');
		var product=this.getAttribute('data_product');
		onclickAdvertisement(type,product_type,product,item_id,id);
	})
	var setline_position_city=function(){
		var province=localStorage.getItem('province');
		var city=localStorage.getItem('city');
		var arr=['北京','天津','上海','重庆','香港','澳门'];
		localStorage.setItem('line_position_province',province);
		localStorage.setItem('line_position_city',city);
		for(var i=0;i<arr.length;i++){
			if(province==arr[i]){
				localStorage.setItem('line_position_province',arr[i]);
				localStorage.setItem('line_position_city',arr[i]);
			}
		}
	};
	setline_position_city();
	
	
	
	
	
// 				mui.ready(function () {
// 					setTimeout(function(){
// 						plus.nativeUI.closeWaiting();
// 						ca.ajaxs({
// 							url:'',
// 							data:{},
// 							succFn:function(data){
// 								localStorage.setItem('line_position_city',city);
// 								localStorage.setItem('line_position2_xianshiname',city);
// 								localStorage.setItem('line_position1_xianshiname',city);
// 							},errFn:function(err){
// 								ca.closeWaiting();
// 								mui.toast('获取企业位置失败！')
// 							}
// 						})
// 						
// 						plus.geolocation.getCurrentPosition(function(p){
// 							// alert(JSON.stringify(p));
// 							// var city=p.address.city.split('市')[0];
// 							
// 							// alert(p.address.district)
// 							// line_position_name
// 						}, function(){
// 							
// 						});
// 					},400)
// 				})
	
	function onclickAdvertisement(type,product_type,product,item_id,id){
		if(type==0){
			mui.openWindow({
				url:'advertisement/advertisement_detail.html',
				id:'advertisement_detail',
				extras:{
					detail_id:id
				}
			})
		}else{
			ca.showWaiting();
			ca.ajaxs({url:'addAdvertisingHits',data:{id:id}});
			if(product_type=='line'){
				var styles={};
				// 在Android5以上设备，如果默认没有开启硬件加速，则强制设置开启
				if(!plus.webview.defaultHardwareAccelerated()&&parseInt(plus.os.version)>=5){
					styles.hardwareAccelerated=true;
				}
				var web=plus.webview.create('line/line_listitem_detail.html','line_listitem_detail',styles,
				{lineid:item_id});
				setTimeout(function(){//延迟0.8s打开
					plus.webview.show(web,'slide-in-right',400);
				},400)
			}else if(product_type=='hotel'){
				ca.showWaiting();
				var detailPage= mui.preload({
					id:'hotel_item_list_detail',
					url:'hotel/hotel_item_list_detail.html'
				});
				if(!detailPage){
					detailPage = plus.webview.getWebviewById('hotel_item_list_detail');
				}
				mui.fire(detailPage,'hotel_item',{
						hotel_id:product,
						enterprise_id:item_id
				});
				
				
				setTimeout(function(){//延迟0.8s打开
					plus.webview.show('hotel_item_list_detail','slide-in-right','450');
				},300)
				
				
			}else if(product_type=='ticket'){
				mui.openWindow({
					url:'scenicspot/scenicspot_list_item_detail.html',
					id:'scenicspot_list_item_detail',
					extras:{
						ticket_item_id:product
					}
				})
			}
			
		}
	}
	ca.receiveNotice('updatePushMsgUnreadCount',function(){
		showweidu();
	});
	showweidu();
	function showweidu(){
		ca.ajaxs({url:'findMyPushMsgUnreadCount',data:{
				user_id:localStorage.getItem('userid')
			},succFn:function(data){
				if(data>0 && data<9){
					ca.id('weidushudiv').style.display='block';
					if(mui.os.ios){
						ca.id('weidushu').style.top=-7+'px';
						ca.id('weidushu').style.left=5+'px';
					}else{
						ca.id('weidushu').style.top=-6+'px';
						ca.id('weidushu').style.left=4+'px';
					}
					ca.id('weidushu').innerText=data;
				}else if(data>9 && data<100){
					ca.id('weidushudiv').style.display='block';
					if(mui.os.ios){
						ca.id('weidushu').style.top=-6+'px';
						ca.id('weidushu').style.left=2+'px';
					}else{
						ca.id('weidushu').style.top=-6+'px';
						ca.id('weidushu').style.left=1+'px';
					}
					ca.id('weidushu').innerText=data;
				}else if(data>99){
					ca.id('weidushu').style.top=-8+'px';
					ca.id('weidushu').style.left=2+'px';
					ca.id('weidushudiv').style.display='block';
					ca.id('weidushu').innerText='...';
				}else{
					ca.id('weidushudiv').style.display='none';
				}
				localStorage.setItem(localStorage.getItem('userid')+'PushMsgUnreadCount',data);
				var arr1 = ['shouye2'];
				ca.sendNotice(arr1,'updatePushMsgUnreadCount',{});
			},errFn:function(err){
				ca.closeWaiting();
			}
		})
	}
					
})