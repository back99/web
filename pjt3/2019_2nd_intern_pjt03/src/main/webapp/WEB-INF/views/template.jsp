<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script type="text/x-handlebars-template" id="titleTemplate">
	
	<li class="item" style="width: 414px;">{{#each productImages}}<img alt=""
		class="img_thumb" src="/reservation/{{this.saveFileName}}">{{/each}} <span class="img_bg"></span>
		<div class="visual_txt">
			<div class="visual_txt_inn">
				<h2 class="visual_txt_tit">
					<span>{{displayInfo.productDescription}}</span>
				</h2>
				<p class="visual_txt_dsc"></p>
			</div>
		</div>
	</li>
	
	</script>

	<script type="text/x-handlebars-template" id="commentsTemplate">
{{#comments}}
	<li class="list_item">
		<div>
			<div class="review_area">
				<div class="thumb_area">
					{{#if commentImages}}
						{{#each commentImages}}
					<a href="#" class="thumb" title="이미지 크게 보기"> <img width="90"
						height="90" class="img_vertical_top"
						src="/reservation/api/image/{{fileId}}"
						alt="리뷰이미지">
					</a> <span class="img_count">1</span>
						{{/each}}
					{{/if}}
				</div>
				<h4 class="resoc_name"></h4>
				<p class="review">{{comment}}</p>
			</div>
			<div class="info_area">
				<div class="review_info">
					<span class="grade">{{score}}</span> <span class="name">{{reservationEmail}}</span> <span
						class="date">{{createDate}} 방문</span>
				</div>
			</div>
		</div>
	</li>
{{/comments}}
	</script>

	<script type="text/x-handlebars-template" id="pathTemplate">
		<div class="detail_location hide">
		<div class="box_store_info no_topline">
			<a href="#" class="store_location" title="지도웹으로 연결"> <img
				class="store_map img_thumb" alt="map"
				src="https://simg.pstatic.net/static.map/image?version=1.1&amp;crs=EPSG:4326&amp;baselayer=bl_vc_bg&amp;exception=xml&amp;scale=2&amp;caller=mw_smart_booking&amp;overlayers=ol_vc_an&amp;center=127.0011948,37.5717079&amp;markers=type,default2,127.0011948,37.5717079&amp;level=11&amp;w=340&amp;h=150">
				<span class="img_border"></span> <span class="btn_map"><i
					class="spr_book2 ico_mapview"></i></span>
			</a>
			<h3 class="store_name">{{displayInfo.placeName}}</h3>
			<div class="store_info">
				<div class="store_addr_wrap">
					<span class="fn fn-pin2"></span>
					<p class="store_addr store_addr_bold">{{displayInfo.placeStreet}} 15</p>
					<p class="store_addr">
						<span class="addr_old">지번</span> <span class="addr_old_detail">{{displayInfo.placeLot}} </span>
					</p>
					<p class="store_addr addr_detail">{{displayInfo.placeName}}</p>
				</div>
				<div class="lst_store_info_wrap">
					<ul class="lst_store_info">
						<li class="item"><span class="item_lt"> <i
								class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
						</span> <span class="item_rt"> <a href="tel:02-548-0597"
								class="store_tel">{{displayInfo.telephone}}</a></span></li>
					</ul>
				</div>
			</div>
			<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
			<div class="bottom_common_path column2">
				<a href="#" class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span>
				</a> <a href="#" class="btn_navigation before"> <i
					class="fn fn-navigation2"></i> <span>내비게이션</span>
				</a>
			</div>
		</div>
	</div>
	</script>

	<script type="text/x-handlebars-template" id="discountTemplate">
		<div>{{#productPrices}}		
			{{priceTypeName}}석 {{discountRate}}%
			{{/productPrices}} 할인
		</div>
	</script>

	<script type="text/x-handlebars-template" id="reserveTemplate">
			{{#reserve}}	
				{{priceTypeName}} {{price}}원({{discountRate}}% 할인)<br>
			{{/reserve}}
	</script>

	<script type="text/x-handlebars-template" id="reservePriceTemplate">
			{{#reserve}}
			<div class="qty">
							<div class="count_control">
								<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
								<div class="clearfix">
									<a
										class="btn_plus_minus spr_book2 ico_minus3 disabled"
										title="빼기"> </a> <input
										class="count_control_input disabled" value="0" id ="count"
										title="수량"> <input type="hidden"
										class="ticket_price" value={{price}}> 
										<input type=hidden id ="reservationInfoId" name = "productPriceId" value={{reservationInfoId}}>
										<input type=hidden id ="productPriceId" name = "productPriceId" value={{productPriceId}}>
										<a
										class="btn_plus_minus spr_book2 ico_plus3" title="더하기"> </a>
								</div>
								<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
								<div class="individual_price">
									<span class="total_price">0</span><span class="price_type">원</span>
								</div>
							</div>
							<div class="qty_info_icon">
								<strong class="product_amount"> <span>{{priceTypeName}}</span>
									
								</strong> <strong class="product_price"> <span class="price">{{price}}</span>
									<span class="price_type">원</span>
								</strong> <em class="product_dsc">{{price}}원 ({{discountRate}}% 할인가)</em>
							</div>
			</div>
			{{/reserve}}
	</script>



	<script type="text/x-handlebars-template" id="confirmedReservationTemplate">
			<article class="card_item" id={{reservationInfoId}}>
								<a href="#" class="link_booking_details">
									<div class="card_body">
										<div class="left"></div>
										<div class="middle">
											<div class="card_detail">
												<em class="booking_number">No.{{reservationInfoId}}</em>
												<h4 class="tit">{{title}}</h4>
												<ul class="detail">
													<li class="item"><span class="item_tit">일정</span> <em
														class="item_dsc" id="reservationDate"> {{reservationStringDate}} </em></li>
													<li class="item"><span class="item_tit">내역</span> <em
														class="item_dsc">
														{{#priceCount}}	
															{{typeName}} {{count}}명
														{{/priceCount}} 
														</em></li>
													<li class="item"><span class="item_tit">장소</span> <em
														class="item_dsc"> {{placeStreet}} </em></li>
													<li class="item"><span class="item_tit">업체</span> <em
														class="item_dsc"> {{placeName}} </em></li>
												</ul>
												<div class="price_summary">
													<span class="price_tit">결제 예정금액</span> <em
														class="price_amount"> <span>{{totalPrice}}</span> <span
														class="unit">원</span>
													</em>
												</div>
												<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
												<div class="booking_cancel">
													<button class="btn">
														<span>취소</span>
													</button>
												</div>

											</div>
										</div>
										<div class="right"></div>
									</div>
									<div class="card_footer">
										<div class="left"></div>
										<div class="middle"></div>
										<div class="right"></div>
									</div>
								</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share"
									title="공유하기"></a>
			</article>
	</script>

	<script type="text/x-handlebars-template"
		id="finishedReservationTemplate">
				<article class="card_item" id={{reservationInfoId}}>
								<a href="#" class="link_booking_details">
									<div class="card_body">
										<div class="left"></div>
										<div class="middle">
											<div class="card_detail">
												<em class="booking_number">No.{{reservationInfoId}}</em>
												<h4 class="tit">{{title}}</h4>
												<ul class="detail">
													<li class="item"><span class="item_tit">일정</span> <em
														class="item_dsc" id="reservationDate"> {{reservationStringDate}} </em></li>
													<li class="item"><span class="item_tit">내역</span> <em
														class="item_dsc"> 
															{{#priceCount}}	
																{{typeName}} {{count}}명
															{{/priceCount}} 
														 </em></li>
													<li class="item"><span class="item_tit">장소</span> <em
														class="item_dsc"> {{placeStreet}} </em></li>
													<li class="item"><span class="item_tit">업체</span> <em
														class="item_dsc"> {{placeName}} </em></li>
												</ul>
												<div class="price_summary">
													<span class="price_tit">결제 예정금액</span> <em
														class="price_amount"> <span>{{totalPrice}}</span> <span
														class="unit">원</span>
													</em>
												</div>
												<div class="booking_cancel">
													<a href="reviewWrite/?reservationInfoId={{reservationInfoId}}&title={{title}}"><button class="btn">
															<span>예매자 리뷰 남기기</span>
														</button></a>
												</div>
											</div>
										</div>
										<div class="right"></div>
									</div>
									<div class="card_footer">
										<div class="left"></div>
										<div class="middle"></div>
										<div class="right"></div>
									</div>
								</a>
			</article>
	</script>

	<script type="text/x-handlebars-template"
		id="cancelReservationTemplate">
			<article class="card_item" id={{reservationInfoId}}>
								<a href="#" class="link_booking_details">
									<div class="card_body">
										<div class="left"></div>
										<div class="middle">
											<div class="card_detail">
												<em class="booking_number">No.{{reservationInfoId}}</em>
												<h4 class="tit">{{title}}</h4>
												<ul class="detail">
													<li class="item"><span class="item_tit">일정</span> <em
														class="item_dsc" id="reservationDate"> {{reservationStringDate}} </em></li>
													<li class="item"><span class="item_tit">내역</span> <em
														class="item_dsc">
															{{#priceCount}}	
																{{typeName}} {{count}}명
															{{/priceCount}} 
														 </em></li>
													<li class="item"><span class="item_tit">장소</span> <em
														class="item_dsc"> {{placeStreet}} </em></li>
													<li class="item"><span class="item_tit">업체</span> <em
														class="item_dsc"> {{placeName}} </em></li>
												</ul>
												<div class="price_summary">
													<span class="price_tit">결제 예정금액</span> <em
														class="price_amount"> <span>{{totalPrice}}</span> <span
														class="unit">원</span>
													</em>
												</div>
											</div>
										</div>
										<div class="right"></div>
									</div>
									<div class="card_footer">
										<div class="left"></div>
										<div class="middle"></div>
										<div class="right"></div>
									</div>
								</a>
			</article>
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js"
		integrity="sha256-ngJY93C4H39YbmrWhnLzSyiepRuQDVKDNCWO2iyMzFw="
		crossorigin="anonymous">
		
	</script>

</body>
</html>