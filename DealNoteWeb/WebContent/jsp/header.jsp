<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<link type="text/css" href="menu/menu.css" rel="stylesheet" />
	<link rel="stylesheet"
		href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
	<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	<script type="text/javascript" src="menu/menu.js"></script>
	<div id="menu">
		<ul class="menu">
			<li><a href="index.jsp" class="parent"><span>Главная</span></a>
<!-- 				<div><ul>
					<li><a href="#" class="parent"><span>Sub Item 1</span></a>
						<div><ul>
							<li><a href="#" class="parent"><span>Sub Item 1.1</span></a>
								<div><ul>
								<li><a href="#"><span>Sub Item 1.1.1</span></a></li>
								<li><a href="#"><span>Sub Item 1.1.2</span></a></li>
								</ul></div>
							</li>
							<li><a href="#"><span>Sub Item 1.2</span></a></li>
							<li><a href="#"><span>Sub Item 1.3</span></a></li>
							<li><a href="#"><span>Sub Item 1.4</span></a></li>
							<li><a href="#"><span>Sub Item 1.5</span></a></li>
							<li><a href="#"><span>Sub Item 1.6</span></a></li>
							<li><a href="#" class="parent"><span>Sub Item 1.7</span></a>
								<div><ul>
								<li><a href="#"><span>Sub Item 1.7.1</span></a></li>
								<li><a href="#"><span>Sub Item 1.7.2</span></a></li>
								</ul></div>
							</li>
						</ul></div>
					</li>
					<li><a href="#"><span>Sub Item 2</span></a></li>
					<li><a href="#"><span>Sub Item 3</span></a></li>
				</ul></div>  -->
			</li>
			<li><a href="#"><span>Справочники</span></a>
				<div><ul>
					<li><a href="#"><span>Агенты</span></a></li>
					<li><a href="#"><span>Товары</span></a></li>
					<li><a href="#"><span>Клиенты</span></a></li>
				</ul></div>
			</li>
			<li><a href="#"><span>Анализ</span></a>
			<div><ul>
					<li><a href="GetAgentGPS"><span>GPS</span></a></li>
					<li><a href="OrderStatServlet"><span>Статистика по заявкам</span></a></li>			
			</ul></div>
			</li>
			<li class="last"><a href="DTraderLogout"><span>Выход</span></a></li>
		</ul>
	</div>	