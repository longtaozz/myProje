(function($, window) {
	    var CLASS_ACTIVE = 'mui-active';
	    var rgbaRegex = /^rgba\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3}),\s*(\d*(?:\.\d+)?)\)$/;
	    var getColor = function(colorStr) {
	        var matches = colorStr.match(rgbaRegex);
	        if (matches && matches.length === 5) {
	            return [
	                matches[1],
	                matches[2],
	                matches[3],
	                matches[4]
	            ];
	        }
	        return [];
	    };
	    var Transparent = function(element, options) {
	        this.element = element;
	        this.options = $.extend({
	            top: 0, //距离顶部高度(到达该高度即触发)
	            offset: 150, //滚动透明距离
	            duration: 16, //过渡时间
	            scrollby: window//监听滚动距离容器
	        }, options || {});
	
	        this.scrollByElem = this.options.scrollby || window;
	        if (!this.scrollByElem) {
	            throw new Error("监听滚动的元素不存在");
	        }
	        this.isNativeScroll = false;
	        if (this.scrollByElem === window) {
	            this.isNativeScroll = true;
	        } else if (!~this.scrollByElem.className.indexOf('mui-scroll-wrapper')) {
	            this.isNativeScroll = true;
	        }
	
	        this._style = this.element.style;
	        this._bgColor = this._style.backgroundColor;
	        var color = getColor(mui.getStyles(this.element, 'backgroundColor'));
	        if (color.length) {
	            this._R = color[0];
	            this._G = color[1];
	            this._B = color[2];
	            this._A = parseFloat(color[3]);
	            this.lastOpacity = this._A;
	            this._bufferFn = $.buffer(this.handleScroll, this.options.duration, this);
	            this.initEvent();
	        } else {
	            throw new Error("元素背景颜色必须为RGBA");
	        }
	    };
	
	    Transparent.prototype.initEvent = function() {
	        this.scrollByElem.addEventListener('scroll', this._bufferFn);
	        if (this.isNativeScroll) { //原生scroll
	            this.scrollByElem.addEventListener($.EVENT_MOVE, this._bufferFn);
	        }
	    }
	    Transparent.prototype.handleScroll = function(e) {
	        var y = window.scrollY;
			var jingru=true;
	        if (!this.isNativeScroll && e && e.detail) {//不是原生滚动
	            y = -e.detail.y;
	        }
	        var opacity = (y - this.options.top) / this.options.offset + this._A;//根据滚动距离计算透明度
	        opacity = Math.min(Math.max(this._A, opacity), 1);//限制最大为 1
	        this._style.backgroundColor = 'rgba(' + this._R + ',' + this._G + ',' + this._B + ',' + opacity + ')';//设置导航栏背景
			if (opacity > this._A) {//如果当前导航栏的透明度大于初始值
					this.element.classList.add(CLASS_ACTIVE);//设置选中
					// ca.blackStatusBar();
					// plus.navigator.setStatusBarStyle('dark');//设置手机状态栏字体是黑色
					ca.id('search').style.backgroundColor='#ffffff';
					// ca.id('header').style.borderBottom='1px solid #e3e3e3';
					jingru=false;
	        }else{
	            this.element.classList.remove(CLASS_ACTIVE);//移除选中
				// ca.whiteStatusBar();
				// plus.navigator.setStatusBarStyle('light');//设置手机状态栏字体是白色
				// ca.id('search').style.border='1px solid rgba(255,255,255,0.8)';
				// ca.id('header').style.borderBottom='';
	        }
					
	        if (this.lastOpacity !== opacity) {
	            $.trigger(this.element, 'alpha', {
	                alpha: opacity
	            });
	            this.lastOpacity = opacity;
	        }
	        
	    };
	    Transparent.prototype.destory = function() {
	        this.scrollByElem.removeEventListener('scroll', this._bufferFn);
	        this.scrollByElem.removeEventListener($.EVENT_MOVE, this._bufferFn);
	        this.element.style.backgroundColor = this._bgColor;
	        this.element.mui_plugin_transparent = null;
	    };
	    $.fn.transparent = function(options) {
	        options = options || {};
	        var transparentApis = [];
	        this.each(function() {
	            var transparentApi = this.mui_plugin_transparent;
	            if (!transparentApi) {
	                var top = this.getAttribute('data-top');
	                var offset = this.getAttribute('data-offset');
	                var duration = this.getAttribute('data-duration');
	                var scrollby = this.getAttribute('data-scrollby');
	                if (top !== null && typeof options.top === 'undefined') {
	                    options.top = top;
	                }
	                if (offset !== null && typeof options.offset === 'undefined') {
	                    options.offset = offset;
	                }
	                if (duration !== null && typeof options.duration === 'undefined') {
	                    options.duration = duration;
	                }
	                if (scrollby !== null && typeof options.scrollby === 'undefined') {
	                    options.scrollby = document.querySelector(scrollby) || window;
	                }
	                transparentApi = this.mui_plugin_transparent = new Transparent(this, options);
	            }
	            transparentApis.push(transparentApi);
	        });
	        return transparentApis.length === 1 ? transparentApis[0] : transparentApis;
	    };
	    $.ready(function() {
	        $('.mui-bar-transparent1').transparent();
	    });
	})(mui, window);