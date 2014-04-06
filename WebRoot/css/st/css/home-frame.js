(function() {
    var _1 = (!!window.ActiveXObject && (!window.XMLHttpRequest || (!!window.XMLHttpRequest && (!document.documentMode || document.documentMode === 7))));

    function HTML5History(_2, _3) {
        var _4 = this;
        this.history = _2;
        this.blankHTML = "/blank.html";
        this._ignoreHashChange = false;
        _2.pushState = function() {
            _4.pushState.apply(_4, arguments);
        };
        if (_3) {
            _4.fireEvent = _3.fireEvent;
            if (_3.blankHTML) {
                _4.blankHTML = _3.blankHTML;
            }
        }
        if (_1) {
            _2._oniframechange = function() {
                _4.onIFrameChange.apply(_4, arguments);
            };
            window.attachEvent("onload", function() {
                _4.firePopState();
                _4.makeIFrame(function() {
                    _4._isInit = true;
                    _4.makeIFrameHistory("");
                });
            });
        } else {
            if (window.addEventListener) {
                window.addEventListener("hashchange", function() {
                    _4.onHashChange.apply(_4, arguments);
                }, false);
                window.addEventListener("load", function() {
                    _4.firePopState();
                }, false);
            } else {
                window.attachEvent("onhashchange", function() {
                    _4.onHashChange.apply(_4, arguments);
                });
                window.attachEvent("onload", function() {
                    _4.firePopState();
                });
            }
        }
    }

    HTML5History.prototype = {

    makeIFrame:function(_5) {
        var _6 = "html5history-iframe";
        var _7 = document.createElement("iframe");
        _7.setAttribute("id", _6);
        if (document.domain != location.hostname) {
            _7.setAttribute("src", "http://" + document.domain + this.blankHTML);
        }
        _7.style.display = "none";
        _7.attachEvent("onload", function() {
            _7.detachEvent("onload", arguments.callee);
            setTimeout(function() {
                _5();
            }, 0);
        });
        document.body.appendChild(_7);
        this.iframe = _7;
    },makeIFrameHistory:function(_8) {
        var _9 = this.iframe.contentWindow.document;
        _9.open();
        _9.write(["<html>","<head>","<meta http-equiv=\"Pragma\" content=\"no-cache\" />","<meta http-equiv=\"Expires\" content=\"-1\" />","<script>",(document.domain != location.hostname) ? "\tdocument.domain=\"" + document.domain + "\";" : "","\tfunction pageLoad() {","\t\ttry { top.window.history._oniframechange(\"" + _8 + "\") } catch(e) {}","\t}","</script>","</head>","<body onload=\"pageLoad()\">","<div id=\"url\">" + _8 + "</div>","</body>","</html>"].join(""));
        _9.title = document.title;
        _9.close();
    },firePopState:function() {
        if (this.fireEvent) {
            this.fireEvent(window, "popstate");
        } else {
            if (document.createEvent) {
                var _a = document.createEvent("UIEvents");
                _a.initEvent("popstate", false, false);
                _a.state = null;
                window.dispatchEvent(_a);
            }
        }
        if (typeof window.onpopstate == "function") {
            window.onpopstate();
        }
    },pushState:function(_b, _c, _d) {
        if (_d.substr(0, 1) != "#" || location.hash == _d) {
            throw "\u7531\u4e8e\u6d4f\u89c8\u5668\u9650\u5236\uff0c\u4f60\u5fc5\u987b\u4f20\u5165\u4e00\u4e2a\u4e0e\u5f53\u524dhash\u4e0d\u540c\u7684hash\u503c";
        }
        this._pushState(_b, _c, _d);
    },_pushState:_1 ? function(_e, _f, url) {
        this._ignoreHashChange = true;
        this.makeIFrameHistory(url);
    } : function(_11, _12, url) {
        this._ignoreHashChange = true;
        window.location.hash = url;
    },onIFrameChange:function(url) {
        if (this._isInit) {
            this._isInit = false;
        } else {
            location.hash = url;
            if (!this._ignoreHashChange) {
                this.firePopState();
            }
            this._ignoreHashChange = false;
        }
    },onHashChange:function(url) {
        if (!this._ignoreHashChange) {
            this.firePopState();
        }
        this._ignoreHashChange = false;
    }};
    HTML5History.bind = function(_16, _17) {
        if (_16.pushState) {
            return;
        }
        new HTML5History(_16, _17);
    };
    window.HTML5History = HTML5History;
})();





object.add("xn.asyncHTML.manager", "events, dom, ua, net, string, xn.asyncHTML.frame", function(_18, _19, dom, ua, net, _1d, xn) {
    var _1f = true;
    var log = function() {
        if (_1f && console) {
            console.log.apply(console, arguments);
        }
    };
    this.AsyncHTMLManager = new Class(function() {
        Class.mixin(this, _19.Events);
        this.initialize = function(_21) {
            _21._buffer = null;
            _21._doctype = ["<!DOCTYPE html [","<!ENTITY nbsp   \"&#160;\">","<!ENTITY iexcl  \"&#161;\">","<!ENTITY cent   \"&#162;\">","<!ENTITY pound  \"&#163;\">","<!ENTITY curren \"&#164;\">","<!ENTITY yen    \"&#165;\">","<!ENTITY brvbar \"&#166;\">","<!ENTITY sect   \"&#167;\">","<!ENTITY uml    \"&#168;\">","<!ENTITY copy   \"&#169;\">","<!ENTITY ordf   \"&#170;\">","<!ENTITY laquo  \"&#171;\">","<!ENTITY not    \"&#172;\">","<!ENTITY shy    \"&#173;\">","<!ENTITY reg    \"&#174;\">","<!ENTITY macr   \"&#175;\">","<!ENTITY deg    \"&#176;\">","<!ENTITY plusmn \"&#177;\">","<!ENTITY sup2   \"&#178;\">","<!ENTITY sup3   \"&#179;\">","<!ENTITY acute  \"&#180;\">","<!ENTITY micro  \"&#181;\">","<!ENTITY para   \"&#182;\">","<!ENTITY middot \"&#183;\">","<!ENTITY cedil  \"&#184;\">","<!ENTITY sup1   \"&#185;\">","<!ENTITY ordm   \"&#186;\">","<!ENTITY raquo  \"&#187;\">","<!ENTITY frac14 \"&#188;\">","<!ENTITY frac12 \"&#189;\">","<!ENTITY frac34 \"&#190;\">","<!ENTITY iquest \"&#191;\">","<!ENTITY Agrave \"&#192;\">","<!ENTITY Aacute \"&#193;\">","<!ENTITY Acirc  \"&#194;\">","<!ENTITY Atilde \"&#195;\">","<!ENTITY Auml   \"&#196;\">","<!ENTITY Aring  \"&#197;\">","<!ENTITY AElig  \"&#198;\">","<!ENTITY Ccedil \"&#199;\">","<!ENTITY Egrave \"&#200;\">","<!ENTITY Eacute \"&#201;\">","<!ENTITY Ecirc  \"&#202;\">","<!ENTITY Euml   \"&#203;\">","<!ENTITY Igrave \"&#204;\">","<!ENTITY Iacute \"&#205;\">","<!ENTITY Icirc  \"&#206;\">","<!ENTITY Iuml   \"&#207;\">","<!ENTITY ETH    \"&#208;\">","<!ENTITY Ntilde \"&#209;\">","<!ENTITY Ograve \"&#210;\">","<!ENTITY Oacute \"&#211;\">","<!ENTITY Ocirc  \"&#212;\">","<!ENTITY Otilde \"&#213;\">","<!ENTITY Ouml   \"&#214;\">","<!ENTITY times  \"&#215;\">","<!ENTITY Oslash \"&#216;\">","<!ENTITY Ugrave \"&#217;\">","<!ENTITY Uacute \"&#218;\">","<!ENTITY Ucirc  \"&#219;\">","<!ENTITY Uuml   \"&#220;\">","<!ENTITY Yacute \"&#221;\">","<!ENTITY THORN  \"&#222;\">","<!ENTITY szlig  \"&#223;\">","<!ENTITY agrave \"&#224;\">","<!ENTITY aacute \"&#225;\">","<!ENTITY acirc  \"&#226;\">","<!ENTITY atilde \"&#227;\">","<!ENTITY auml   \"&#228;\">","<!ENTITY aring  \"&#229;\">","<!ENTITY aelig  \"&#230;\">","<!ENTITY ccedil \"&#231;\">","<!ENTITY egrave \"&#232;\">","<!ENTITY eacute \"&#233;\">","<!ENTITY ecirc  \"&#234;\">","<!ENTITY euml   \"&#235;\">","<!ENTITY igrave \"&#236;\">","<!ENTITY iacute \"&#237;\">","<!ENTITY icirc  \"&#238;\">","<!ENTITY iuml   \"&#239;\">","<!ENTITY eth    \"&#240;\">","<!ENTITY ntilde \"&#241;\">","<!ENTITY ograve \"&#242;\">","<!ENTITY oacute \"&#243;\">","<!ENTITY ocirc  \"&#244;\">","<!ENTITY otilde \"&#245;\">","<!ENTITY ouml   \"&#246;\">","<!ENTITY divide \"&#247;\">","<!ENTITY oslash \"&#248;\">","<!ENTITY ugrave \"&#249;\">","<!ENTITY uacute \"&#250;\">","<!ENTITY ucirc  \"&#251;\">","<!ENTITY uuml   \"&#252;\">","<!ENTITY yacute \"&#253;\">","<!ENTITY thorn  \"&#254;\">","<!ENTITY yuml   \"&#255;\">","<!ENTITY fnof     \"&#402;\">","<!ENTITY Alpha    \"&#913;\">","<!ENTITY Beta     \"&#914;\">","<!ENTITY Gamma    \"&#915;\">","<!ENTITY Delta    \"&#916;\">","<!ENTITY Epsilon  \"&#917;\">","<!ENTITY Zeta     \"&#918;\">","<!ENTITY Eta      \"&#919;\">","<!ENTITY Theta    \"&#920;\">","<!ENTITY Iota     \"&#921;\">","<!ENTITY Kappa    \"&#922;\">","<!ENTITY Lambda   \"&#923;\">","<!ENTITY Mu       \"&#924;\">","<!ENTITY Nu       \"&#925;\">","<!ENTITY Xi       \"&#926;\">","<!ENTITY Omicron  \"&#927;\">","<!ENTITY Pi       \"&#928;\">","<!ENTITY Rho      \"&#929;\">","<!ENTITY Sigma    \"&#931;\">","<!ENTITY Tau      \"&#932;\">","<!ENTITY Upsilon  \"&#933;\">","<!ENTITY Phi      \"&#934;\">","<!ENTITY Chi      \"&#935;\">","<!ENTITY Psi      \"&#936;\">","<!ENTITY Omega    \"&#937;\">","<!ENTITY alpha    \"&#945;\">","<!ENTITY beta     \"&#946;\">","<!ENTITY gamma    \"&#947;\">","<!ENTITY delta    \"&#948;\">","<!ENTITY epsilon  \"&#949;\">","<!ENTITY zeta     \"&#950;\">","<!ENTITY eta      \"&#951;\">","<!ENTITY theta    \"&#952;\">","<!ENTITY iota     \"&#953;\">","<!ENTITY kappa    \"&#954;\">","<!ENTITY lambda   \"&#955;\">","<!ENTITY mu       \"&#956;\">","<!ENTITY nu       \"&#957;\">","<!ENTITY xi       \"&#958;\">","<!ENTITY omicron  \"&#959;\">","<!ENTITY pi       \"&#960;\">","<!ENTITY rho      \"&#961;\">","<!ENTITY sigmaf   \"&#962;\">","<!ENTITY sigma    \"&#963;\">","<!ENTITY tau      \"&#964;\">","<!ENTITY upsilon  \"&#965;\">","<!ENTITY phi      \"&#966;\">","<!ENTITY chi      \"&#967;\">","<!ENTITY psi      \"&#968;\">","<!ENTITY omega    \"&#969;\">","<!ENTITY thetasym \"&#977;\">","<!ENTITY upsih    \"&#978;\">","<!ENTITY piv      \"&#982;\">","<!ENTITY bull     \"&#8226;\">","<!ENTITY hellip   \"&#8230;\">","<!ENTITY prime    \"&#8242;\">","<!ENTITY Prime    \"&#8243;\">","<!ENTITY oline    \"&#8254;\">","<!ENTITY frasl    \"&#8260;\">","<!ENTITY weierp   \"&#8472;\">","<!ENTITY image    \"&#8465;\">","<!ENTITY real     \"&#8476;\">","<!ENTITY trade    \"&#8482;\">","<!ENTITY alefsym  \"&#8501;\">","<!ENTITY larr     \"&#8592;\">","<!ENTITY uarr     \"&#8593;\">","<!ENTITY rarr     \"&#8594;\">","<!ENTITY darr     \"&#8595;\">","<!ENTITY harr     \"&#8596;\">","<!ENTITY crarr    \"&#8629;\">","<!ENTITY lArr     \"&#8656;\">","<!ENTITY uArr     \"&#8657;\">","<!ENTITY rArr     \"&#8658;\">","<!ENTITY dArr     \"&#8659;\">","<!ENTITY hArr     \"&#8660;\">","<!ENTITY forall   \"&#8704;\">","<!ENTITY part     \"&#8706;\">","<!ENTITY exist    \"&#8707;\">","<!ENTITY empty    \"&#8709;\">","<!ENTITY nabla    \"&#8711;\">","<!ENTITY isin     \"&#8712;\">","<!ENTITY notin    \"&#8713;\">","<!ENTITY ni       \"&#8715;\">","<!ENTITY prod     \"&#8719;\">","<!ENTITY sum      \"&#8721;\">","<!ENTITY minus    \"&#8722;\">","<!ENTITY lowast   \"&#8727;\">","<!ENTITY radic    \"&#8730;\">","<!ENTITY prop     \"&#8733;\">","<!ENTITY infin    \"&#8734;\">","<!ENTITY ang      \"&#8736;\">","<!ENTITY and      \"&#8743;\">","<!ENTITY or       \"&#8744;\">","<!ENTITY cap      \"&#8745;\">","<!ENTITY cup      \"&#8746;\">","<!ENTITY int      \"&#8747;\">","<!ENTITY there4   \"&#8756;\">","<!ENTITY sim      \"&#8764;\">","<!ENTITY cong     \"&#8773;\">","<!ENTITY asymp    \"&#8776;\">","<!ENTITY ne       \"&#8800;\">","<!ENTITY equiv    \"&#8801;\">","<!ENTITY le       \"&#8804;\">","<!ENTITY ge       \"&#8805;\">","<!ENTITY sub      \"&#8834;\">","<!ENTITY sup      \"&#8835;\">","<!ENTITY nsub     \"&#8836;\">","<!ENTITY sube     \"&#8838;\">","<!ENTITY supe     \"&#8839;\">","<!ENTITY oplus    \"&#8853;\">","<!ENTITY otimes   \"&#8855;\">","<!ENTITY perp     \"&#8869;\">","<!ENTITY sdot     \"&#8901;\">","<!ENTITY lceil    \"&#8968;\">","<!ENTITY rceil    \"&#8969;\">","<!ENTITY lfloor   \"&#8970;\">","<!ENTITY rfloor   \"&#8971;\">","<!ENTITY lang     \"&#9001;\">","<!ENTITY rang     \"&#9002;\">","<!ENTITY loz      \"&#9674;\">","<!ENTITY spades   \"&#9824;\">","<!ENTITY clubs    \"&#9827;\">","<!ENTITY hearts   \"&#9829;\">","<!ENTITY diams    \"&#9830;\">","<!ENTITY quot    \"&#34;\">","<!ENTITY OElig   \"&#338;\">","<!ENTITY oelig   \"&#339;\">","<!ENTITY Scaron  \"&#352;\">","<!ENTITY scaron  \"&#353;\">","<!ENTITY Yuml    \"&#376;\">","<!ENTITY circ    \"&#710;\">","<!ENTITY tilde   \"&#732;\">","<!ENTITY ensp    \"&#8194;\">","<!ENTITY emsp    \"&#8195;\">","<!ENTITY thinsp  \"&#8201;\">","<!ENTITY zwnj    \"&#8204;\">","<!ENTITY zwj     \"&#8205;\">","<!ENTITY lrm     \"&#8206;\">","<!ENTITY rlm     \"&#8207;\">","<!ENTITY ndash   \"&#8211;\">","<!ENTITY mdash   \"&#8212;\">","<!ENTITY lsquo   \"&#8216;\">","<!ENTITY rsquo   \"&#8217;\">","<!ENTITY sbquo   \"&#8218;\">","<!ENTITY ldquo   \"&#8220;\">","<!ENTITY rdquo   \"&#8221;\">","<!ENTITY bdquo   \"&#8222;\">","<!ENTITY dagger  \"&#8224;\">","<!ENTITY Dagger  \"&#8225;\">","<!ENTITY permil  \"&#8240;\">","<!ENTITY lsaquo  \"&#8249;\">","<!ENTITY rsaquo  \"&#8250;\">","<!ENTITY euro   \"&#8364;\">","]>"].join("");
            _21._xslt = ["<xsl:stylesheet version=\"1.0\"","\texclude-result-prefixes=\"\"","\txmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"","\txmlns=\"http://www.w3.org/1999/xhtml\">","","\t<xsl:output method=\"html\" encoding=\"utf-8\" />","","\t<xsl:template match=\"*[local-name() = 'script']\">","\t\t<script type=\"text/javascript\">","\t\t\t<xsl:value-of select=\".\" disable-output-escaping=\"yes\" />","\t\t</script>","\t</xsl:template>","","\t<xsl:template match=\"node() | @*\">","\t\t<xsl:copy>","\t\t\t<xsl:apply-templates select=\"node() | @*\" />","\t\t</xsl:copy>","\t</xsl:template>","","\t<xsl:template match=\"*[@ui-badformed]\">","\t\t<xsl:copy>","\t\t\t<xsl:apply-templates select=\"@*\" />","\t\t\t<xsl:value-of select=\".\" disable-output-escaping=\"yes\" />","\t\t</xsl:copy>","\t</xsl:template>","","</xsl:stylesheet>"].join("");
            _21.__timer = {setTimeout:setTimeout,setInterval:setInterval};
            object.use("dom", function(_22, dom) {
                _21.dom = dom;
            });
            _21.__domModuleFn = object._loader.lib.dom.fn || object._loader.lib.dom.factory;
            _21.rendering = false;
            _21.location = _21.parseURL(location.href.replace(/#.*/ig, ""));
            _21.hashname = "";
            _21.prefetchLoad = false;
            _21.prefetchLoadStyleNode = null;
            _21._lastHeadElements = [];
            _21.head = document.getElementsByTagName("head")[0];
            _21._needEval = null;
        };
        this.parseURL = function(_24, url) {
            var ele = document.createElement("a");
            ele.href = url;
            return ele;
        };
        this.parseHash = function(_27, url) {
            url = _27.parseURL(url);
            var _29 = "",_2a = "",_2b = "";
            if (url.protocol != location.protocol) {
                _29 = url.protocol;
            }
            if (url.hostname != location.hostname) {
                _2a = url.hostname.replace("." + document.domain, "");
            }
            if (url.port != "80" && url.port != "0" && url.port != location.port) {
                _2b = url.port;
            }
            var _2c = "";
            if (_29) {
                _2c += _29 + "//";
            }
            if (_2a) {
                _2c += (_29 ? "" : "//") + _2a;
            }
            if (_2b) {
                _2c += ":" + _2b;
            }
            if (url.pathname.slice(0, 1) != "/") {
                _2c += "/";
            }
            _2c += url.pathname + url.search;
            return _2c;
        };
        this.deparseHash = function(_2d, _2e) {
            var url = _2d.parseURL(location.href);
            var _30 = /^(\w+?\:)?(\/\/([\w\-_.]*))?(:(\d+))?(\/.*?)?(\?(.*?))?$/i;
            var _31 = _30.exec(_2e);
            if (!_31) {
                return "";
            }
            var _32 = _31[1];
            var _33 = _31[3];
            var _34 = _31[5];
            var _35 = _31[6];
            var _36 = _31[7];
            if (!_32 && !_33 && !_34 && !_35 && !_36) {
                return "";
            }
            var _37 = "";
            _37 += _32 || url.protocol;
            _37 += "//";
            _37 += _33 ? _33 + "." + document.domain : url.hostname;
            if (_34) {
                _37 += ":" + _34;
            }
            _37 += _35 || "";
            _37 += _36 || "";
            return _37;
        };
        this.putBuffer = function(_38, url) {
            _38._buffer = url;
        };
        this.runBuffer = function(_3a) {
            if (_3a._buffer) {
                _3a.open(_3a._buffer);
                _3a._buffer = null;
            }
        };
        this.open = function(_3b, url, _3d) {
            if (!url) {
                return;
            }
            if (_3b.rendering === true) {
                _3b.putBuffer(url);
                return;
            }
            var _3e = _3b.parseHash(url);
            var _3f = false;
            if (url == _3b.location.href) {
                _3f = true;
            } else {
                _3b.location = _3b.parseURL(url);
                _3b.hashname = _3b.parseHash(url);
                window.history.pushState({}, null, "#" + _3b.hashname);
            }
            _3b.asyncHTML(url, _3f, _3d);
        };
        var _40 = true;
        this.listenHash = function(_41, _42) {
            if (_40) {
                _40 = false;
                return;
            }
            var url = _42 ? _41.deparseHash(_42) : location.href.replace(/\#.*$/ig, "");
            if (!url) {
                return;
            }
            if (_41.rendering === true) {
                _41.pubBuffer(url);
                return;
            }
            _41.location = _41.parseURL(url);
            _41.hashname = _41.parseHash(url);
            _41.asyncHTML(url);
        };
        this.initPage = function(_44, _45) {
            var url = location.href.replace(/#.*$/i, "");
            if (location.href.indexOf("#") != -1) {
                var _47 = location.href.substr(location.href.indexOf("#") + 1);
                var _48 = _44.deparseHash(_47);
                if (_48) {
                    url = _48;
                }
            }
            _44.location = _44.parseURL(url);
            _44.hashname = _44.parseHash(url);
            dom.ready(function() {
                var _49 = document.body.className.match(/layout_(.+)/i);
                if (_49) {
                    _44.defaultLayout = _49[1];
                }
            });
            if (_45) {
                if (_44.location.hostname != location.hostname || _44.location.pathname.replace(/^\//i, "") != location.pathname.replace(/^\//i, "")) {
                    _44.prefetchLoad = true;
                }
                dom.ready(function() {
                    _44.fireReady();
                    _44._lastHeadElements = dom.getElements("head [ui-rel~=prefetchframe]");
                });
                _44.fireEvent("beforeload");
                dom.ready(function() {
                    _44.fireEvent("load");
                });
                if (_44.prefetchLoad) {
                    if (ua.ua.ie) {
                        var _4a = document.createStyleSheet();
                        _4a.cssText = ".x-prefetchframecontent * {display:none}";
                    } else {
                        var _4b = document.createElement("style");
                        _4b.setAttribute("type", "text/css");
                        var _4c = document.createTextNode(".x-prefetchframecontent{display:none}");
                        _4b.appendChild(_4c);
                        _44.head.appendChild(_4b);
                        _44.prefetchLoadStyleNode = _4b;
                    }
                    dom.ready(function() {
                        _44.asyncHTML(url);
                    });
                }
            } else {
                dom.ready(function() {
                    _44.asyncHTML(url);
                });
            }
        };
        this.startLoadFrame = function(_4d) {
            _4d.overwriteTimer();
            _4d.overwriteDomModule();
            dom.ready(function() {
                window.__logEvents = true;
            });
            object.use("dom", function(_4e, dom) {
                if (window.XN) {
                    window.XN.util.setTimeout = window.setTimeout;
                    window.XN.util.setInterval = window.setInterval;
                    window.XN.dom.ready = window.XN.dom.readyDo = dom.ready;
                    window.XN.element.addEvent = window.XN.event.addEvent = function(ele, _51, _52, cap) {
                        var _54 = arguments.callee;
                        if (Array.isArray(ele)) {
                            ele.forEach(function(one) {
                                _54(one, _51, _52, cap);
                            });
                        } else {
                            if (typeof ele == "string") {
                                ele = document.getElementById(ele);
                            }
                            if (!ele._eventListeners) {
                                ele._eventListeners = {};
                            }
                            if (!ele.wrapEvent) {
                                ele.wrapEvent = function(e) {
                                    return _19.wrapEvent(e);
                                };
                            }
                            dom.Element.prototype.addEvent.call(ele, _51, _52, cap);
                            return ele;
                        }
                    };
                    window.XN.element.delEvent = function(_57, _58, _59, cap) {
                        dom.Element.prototype.removeEvent.call(_57, _58, _59, cap);
                    };
                    window.XN.event.delEvent = function(_5b, _5c, _5d, cap) {
                        dom.Element.prototype.removeEvent.call(_5b, _5c, _5d, cap);
                    };
                    window.XN.clearFiles();
                }
            });
            _4d.fireLoading();
        };
        this.stopLoadFrame = function(_5f) {
        };
        this.fireError = function(_60, msg, _62) {
            _62 = _62 || 10;
            var _63 = new Error(msg);
            _63.number = _62;
            _60.fireEvent("error", _63);
            _60.rendering = false;
        };
        this.loadRequestHTML = function(_64, r) {
            _64.rendering = true;
            var _66 = r.responseText;
            var _67 = _66.substring(_66.indexOf("<head"), _66.indexOf("</head>"));
            _67 = _67.substring(_67.indexOf(">") + 1, _67.length);
            var _68 = _66.substring(_66.indexOf("<body"), _66.indexOf("</body>"));
            _68 = _68.substring(_68.indexOf(">") + 1, _68.length);
            var _69 = _66.match(/<body(.*)>/i);
            if (!_67 || !_68) {
                _64.fireError("\u7f51\u7edc\u8fd4\u56dehtml\u6587\u6863\u683c\u5f0f\u4e0d\u6b63\u786e");
                return;
            }
            _68 = "<div " + _69[1] + ">" + _68 + "</div>";
            _68 = dom.Element.fromString(_68);
            var xml,_6b;
            if (ua.ua.ie) {
                xml = new ActiveXObject("Microsoft.XMLDOM");
                xml.async = false;
                xml.validateOnParse = false;
                xml.loadXML("<head xmlns=\"http://www.w3.org/1999/xhtml\">" + _67 + "</head>");
                _6b = xml.documentElement;
            } else {
                var _6c = new DOMParser();
                xml = _6c.parseFromString("<head xmlns=\"http://www.w3.org/1999/xhtml\">" + _67 + "</head>", "text/xml");
                _6b = xml.documentElement;
            }
            _64.renderHTML(_6b, _68);
            window.scrollTo(0, 0);
            _64.rendering = false;
        };
        this.loadRequest = function(_6d, r) {
            _6d.rendering = true;
            var xml,_70,_71;
            if (ua.ua.ie) {
                var _72 = r.responseText;
                _72 = _72.replace(/<!DOCTYPE.+?>/mig, _6d._doctype);
                xml = new ActiveXObject("Microsoft.XMLDOM");
                xml.async = false;
                xml.validateOnParse = false;
                xml.loadXML(_72);
                if (!xml.xml) {
                    _6d.fireError("\u7f51\u7edc\u8fd4\u56deXML\u6587\u6863\u683c\u5f0f\u4e0d\u6b63\u786e\n" + xml.parseError.srcText + xml.parseError.reason);
                    return;
                }
            } else {
                if (!r.responseXML || !r.responseXML.documentElement || r.responseXML.getElementsByTagName("parsererror").length) {
                    _6d.fireError("\u7f51\u7edc\u8fd4\u56deXML\u6587\u6863\u683c\u5f0f\u4e0d\u6b63\u786e");
                    return;
                }
                xml = r.responseXML;
            }
            if (xml.documentElement.getElementsByTagName("error").length) {
                _70 = parseInt(xml.documentElement.getElementsByTagName("error").item(0).getAttribute("code"));
                _6d.fireError("\u7f51\u7edc\u8fd4\u56deXML\u6587\u6863\u683c\u5f0f\u4e0d\u6b63\u786e", _70);
            }
            var _73 = xml.documentElement.getElementsByTagName("head")[0];
            var _74 = xml.documentElement.getElementsByTagName("body")[0];
            var _75 = document.createElement("div");
            for (var _76,i = 0; i < _74.childNodes.length; i++) {
                _76 = _74.childNodes[i];
                if (_76.nodeType === 1) {
                    _76 = _6d.xml2html(_76);
                    _75.appendChild(_76);
                }
            }
            _6d.renderHTML(_73, _75);
            window.scrollTo(0, 0);
            _6d.rendering = false;
        };
        this.asyncHTML = function(_78, url, _7a, _7b) {
            if (!_7b) {
                _7b = "get";
            }
            var _7c = _7a ? "async-html-reload" : "async-html";
            _78.fireEvent("beforeload");
            var _7d = new net.Request({url:url + (url.indexOf("?") != -1 ? "&" : "?") + "__view=" + _7c,method:_7b,headers:{"Content-Type":"application/x-www-form-urlencoded","If-Modified-Since":"0","Cache-Control":"no-cache"}});
            _7d.onsuccess = function(_7e) {
                if (url != _78.location.href) {
                    return;
                }
                var xhr = _7e.request;
                dom.ready(function() {
                    if (xhr.getResponseHeader("Content-Type").indexOf("text/xml") != -1) {
                        _78.loadRequest(xhr);
                    }
                    if (xhr.getResponseHeader("Content-Type").indexOf("text/html") != -1) {
                        _78.loadRequestHTML(xhr);
                    }
                });
                _78.runBuffer();
            };
            _7d.onerror = function(_80) {
                _78.fireError("\u7f51\u7edc\u95ee\u9898");
            };
            _7d.send(null);
        };
        this.renderHTML = function(_81, _82, _83) {
            xn.asyncHTML.frame.clearAllTimer();
            _81.startLoadFrame();
            _81.fixSelectInIE6();
            _81.checkEval();
            var _84 = [];
            var _85 = function() {
                xn.asyncHTML.frame.clearEvents();
                _81.clearHeadElements();
                _81._lastHeadElements = _84;
                _81.renderBody(_83);
                _81.fireEvent("unload");
                var _86 = _81.__timer.setTimeout;
                _86(function() {
                    _81.fireReady();
                }, 0);
                _81.fireEvent("load");
            };
            var _87 = [];
            for (var i = 0; i < _82.childNodes.length; i++) {
                var _89 = _82.childNodes[i];
                if (_89.nodeType === 1) {
                    _87.push(_89);
                }
            }
            var _8a = 0;
            var _8b = function() {
                if (_8a === _87.length) {
                    _85();
                    return;
                }
                var ele = _87[_8a];
                _8a++;
                if (ele.tagName == "link" || ele.tagName == "style") {
                    ele = _81.xml2html(ele);
                    _84.push(ele);
                    _81.head.appendChild(ele);
                    _8b();
                } else {
                    if (ele.tagName == "script") {
                        var src = ele.getAttribute("src");
                        if (src) {
                            (window.Loader || object.Loader).loadScript(src, function(ele) {
                                _84.push(ele);
                                _8b();
                            });
                        } else {
                            ele = _81.xml2html(ele);
                            _81.head.appendChild(ele);
                            if (ua.ua.gecko) {
                                eval.call(window, ele.innerHTML);
                            }
                            _84.push(ele);
                            _8b();
                        }
                    } else {
                        if (ele.tagName == "title") {
                            document.title = ua.ua.ie ? ele.text : ele.textContent;
                            _8b();
                        } else {
                            _8b();
                        }
                    }
                }
            };
            _8b();
        };
        this.renderBody = function(_8f, _90) {
            if (_8f.prefetchLoadStyleNode && _8f.prefetchLoadStyleNode.parentNode) {
                _8f.prefetchLoadStyleNode.parentNode.removeChild(_8f.prefetchLoadStyleNode);
            }
            var _91 = _90.getAttribute("ui-layout");
            if (_91) {
                document.body.className = "layout_" + _91;
            } else {
                document.body.className = "layout_" + _8f.defaultLayout;
            }
            for (var i = 0; i < _90.childNodes.length; i++) {
                var _93 = _90.childNodes[i];
                if (_93.nodeType != 1) {
                    continue;
                }
                var id = _93.getAttribute("id");
                if (!id) {
                    continue;
                }
                var _95 = dom.getElement("#" + id);
                if (!_95) {
                    continue;
                }
                _95.parentNode.replaceChild(_93, _95);
                i--;
                _8f.evalScripts(_93);
                if (_8f.prefetchLoad && ua.ua.gecko < 2) {
                    var _96 = _93.getElementsByTagName("iframe");
                    for (var j = 0; j < _96.length; j++) {
                        _96[j].src = _96[j].src;
                    }
                }
            }
            if (ua.ua.opera) {
                var _98 = dom.id("main2");
                _98.innerHTML += "";
            }
        };
        this.clearHeadElements = function(_99) {
            var _9a = _99._lastHeadElements;
            for (var _9b,i = 0; i < _9a.length; i++) {
                _9b = _9a[i];
                _9b.parentNode.removeChild(_9b);
            }
            _9a.length = 0;
        };
        this.overwriteTimer = function(_9d) {
            window.__timeouts = [];
            window.__intervals = [];
            window.setTimeout = function(a1, a2) {
                var _a0 = _9d.__timer.setTimeout;
                var _a1 = _a0(a1, a2);
                window.__timeouts.push(_a1);
                return _a1;
            };
            window.setInterval = function(a1, a2) {
                var _a4 = _9d.__timer.setInterval;
                var _a5 = _a4(a1, a2);
                window.__intervals.push(_a5);
                return _a5;
            };
        };
        this.recoverTimer = function(_a6) {
            window.setTimeout = _a6.__timer.setTimeout;
            window.setInterval = _a6.__timer.setInterval;
        };
        this.overwriteDomModule = function(_a7) {
            var pkg = object._loader.lib.dom;
            pkg.factory = pkg.fn = function() {
                _a7.__domModuleFn.apply(this, arguments);
                xn.asyncHTML.frame.wrap(this);
            };
        };
        this.recoverDomModule = function(_a9) {
            var pkg = object._loader.lib.dom;
            pkg.factory = pkg.fn = _a9.__domModuleFn;
        };
        this.fireLoading = function() {
            window.__domLoading = true;
            window.__asyncDomloadHooks = [];
            window.__firer = dom.wrap(document.createElement("div"));
        };
        this.__runHooks = function() {
            window.__asyncDomloadHooks.forEach(function(f, i) {
                try {
                    f();
                }
                catch(e) {
                }
            });
            window.__asyncDomloadHooks = [];
        };
        this.fireReady = function(_ad) {
            window.__domLoading = false;
            if ((ua.ua.webkit && ua.ua.webkit < 525) || !document.addEventListener) {
                _ad.__runHooks();
            }
            if (document.addEventListener) {
                window.__firer.fireEvent("AsyncDOMContentLoaded");
            }
        };
        this.fixSelectInIE6 = function(_ae) {
            if (ua.ua.ie !== 6) {
                return;
            }
            var _af = document.getElementsByTagName("select");
            if (_af) {
                for (var j = 0; j < _af.length; j++) {
                    _af[j].style.visibility = "hidden";
                }
            }
        };
        this.checkEval = function(_b1) {
            if (_b1._needEval != null) {
                return;
            }
            window.__testEval = 0;
            var div = document.createElement("div");
            div.innerHTML = "<script type=\"text/javascript\">window.__testEval += 1;</script>";
            document.body.appendChild(div);
            if (window.__testEval != 0) {
                _b1._needEval = false;
            } else {
                _b1._needEval = true;
            }
            div.parentNode.removeChild(div);
            window.__testEval = null;
        };
        this.evalScripts = function(_b3, _b4) {
            if (_b3._needEval) {
                var _b5 = _b4.getElementsByTagName("script");
                for (var _b6,i = 0; i < _b5.length; i++) {
                    _b6 = _b5[i];
                    try {
                        if (ua.ua.ie) {
                            window.execScript(_b6.innerHTML);
                        } else {
                            window.eval(_b6.innerHTML);
                        }
                    }
                    catch(e) {
                    }
                }
            }
        };
        this.bind = function(_b8, ele) {
            dom.wrap(document.body).delegate("a", "click", function(e) {
                if (this.getAttribute("ui-async") || (this.hostname == _b8.location.hostname && this.pathname == _b8.location.pathname && this.href.indexOf("#") == -1)) {
                    e.preventDefault();
                    if (dom.id("homeLoverTipBox")) {
                        dom.id("homeLoverTipBox").hide();
                    }
                    _b8.open(_1d.trim(this.href));
                }
            });
            dom.wrap(document.body).delegate("form[ui-async=async] *[type=submit]", "click", function(e) {
                e.preventDefault();
                if (this.form.onsubmit) {
                    var r = this.form.onsubmit();
                    if (!r) {
                        return;
                    }
                }
                var url = this.form.action;
                var _be = "";
                for (var _bf,i = 0; i < this.form.elements.length; i++) {
                    _bf = this.form.elements[i];
                    if (_bf.name) {
                        _be += (_bf.name + "=" + encodeURIComponent(_bf.value) + "&");
                    }
                }
                _be = _be.slice(0, -1);
                _b8.open(url + (url.indexOf("?") != -1 ? "&" : "?") + _be);
            });
        };
        this.xml2html = function(_c1, _c2) {
            if (ua.ua.ie) {
                if (_c2.tagName == "link") {
                    return document.createElement(_c2.xml);
                } else {
                    if (_c2.tagName == "script") {
                        var tmp = document.createElement("script");
                        tmp.text = _c2.text;
                        return tmp;
                    }
                }
                var xsl = new ActiveXObject("Microsoft.XMLDOM");
                xsl.async = false;
                xsl.loadXML(_c1._xslt);
                var _c5 = _c2.transformNode(xsl);
                _c2 = dom.Element.fromString(_c5);
                return _c2;
            } else {
                if (ua.ua.webkit || ua.ua.opera) {
                    if (_c2.tagName == "script") {
                        tmp = document.createElement("script");
                        tmp.textContent = _c2.textContent;
                        _c2 = tmp;
                    } else {
                        if (_c2.tagName == "link") {
                            tmp = document.createElement("link");
                            tmp.href = _c2.href;
                            tmp.rel = _c2.rel;
                            tmp.type = _c2.type;
                            tmp.media = _c2.media;
                            tmp.textContent = _c2.textContent;
                            _c2 = tmp;
                        } else {
                            _c1.replaceBadFormed(_c2);
                            if (ua.ua.webkit) {
                                _c1.replaceCData(_c2);
                            }
                            _c2 = document.importNode(_c2, true);
                        }
                    }
                } else {
                    _c1.replaceBadFormed(_c2);
                }
            }
            return _c2;
        };
        this.replaceCData = function(_c6, _c7) {
            var as = _c7.getElementsByTagName("*");
            for (var i = 0,_ca = as.length; i < _ca; i++) {
                for (var j = 0,_cc = as[i].childNodes.length; j < _cc; j++) {
                    if (as[i].childNodes[j].nodeType == 4) {
                        var t = document.createTextNode();
                        t.nodeValue = as[i].childNodes[j].textContent;
                        as[i].replaceChild(t, as[i].childNodes[j]);
                        break;
                    }
                }
            }
        };
        this.replaceBadFormed = function(_ce, _cf) {
            var _d0 = dom.getElements("*[ui-badformed]", _cf);
            for (var i = 0; i < _d0.length; i++) {
                var tmp = dom.getDom(_d0[i].textContent);
                _d0[i].innerHTML = "";
                while (tmp.firstChild) {
                    _d0[i].appendChild(tmp.firstChild);
                }
            }
        };
    });
});
object.add("xn.asyncHTML.frame", "ua, dom, events", function(_d3, ua, dom, _d6) {
    this.wrap = function(_d7) {
        _d7.ready = function(_d8) {
            if (window.__domLoading) {
                if ((ua.ua.webkit && ua.ua.webkit < 525) || !document.addEventListener) {
                    window.__asyncDomloadHooks.push(_d8);
                }
                if (document.addEventListener) {
                    window.__firer.addEventListener("AsyncDOMContentLoaded", _d8, false);
                }
            } else {
                _d8();
            }
        };
        var _d9 = _d7.Element.prototype.addEvent;

        function addEvent(_da, _db, _dc, cap) {
            if (window.__logEvents && !(_d6.HOLD & cap)) {
                if (!window.__events) {
                    window.__events = [];
                }
                window.__events.push({node:_da,type:_db,func:_dc});
            }
            _d9.call(_da, _db, _dc, cap);
        }

        _d7.Element.__mixin__("addEvent", addEvent);
        window.addEvent = document.addEvent = function(_de, _df, cap) {
            addEvent(this, _de, _df, cap);
        };
    };
    this.clearEvents = function() {
        if (window.__events) {
            window.__events.forEach(function(_e1) {
                dom.Element.prototype.removeEvent.call(_e1.node, _e1.type, _e1.func);
            });
            window.__events = [];
        }
    };
    this.clearAllTimer = function() {
        var i;
        if (window.__timeouts) {
            for (i = 0; i < window.__timeouts.length; i++) {
                clearTimeout(window.__timeouts[i]);
            }
        }
        if (window.__intervals) {
            for (i = 0; i < window.__intervals.length; i++) {
                clearInterval(window.__intervals[i]);
            }
        }
        window.__timeouts = [];
        window.__intervals = [];
    };
});
function switchSkin() {
    new Ajax.Request("http://i." + XN.env.domain + "/store/hometpl/list", {method:"get",onSuccess:function(o) {
        $("zidou_template").innerHTML = o.responseText;
        $("zidou_template").eval_inner_JS();
    }});
}
XN.dom.ready(function() {
    if (location.hash == "#switchSkin") {
        switchSkin();
    }
});
if (XN.browser.IE) {
    setInterval(function() {
        if (document.title.indexOf("#") != -1) {
            document.title = document.title.replace(/#.*$/i, "");
        }
    }, 200);
}
function TreeView(_e4, _e5) {
    this.parent = null;
    this.parentEle = null;
    this.eles = _e4;
    this.branches = [];
    this.options = {className:"selected",openedClassName:"opened",hideBranches:false,autoActive:true,checkEvent:function(e) {
        return true;
    }};
    this.initialize(_e5);
}
(function TreeViewPrototype() {
    this.traversal = function(_e7) {
        _e7(this);
        for (var i = 0; i < this.branches.length; i++) {
            this.branches[i].traversal(_e7);
        }
    };
    this.addBranch = function(ele, _ea, _eb) {
        var _ec = new TreeView(_ea, _eb);
        _ec.parent = this;
        _ec.parentEle = ele;
        this.branches.push(_ec);
        return _ec;
    };
    this.getBranch = function(ele) {
        for (var i = 0; i < this.branches.length; i++) {
            if (this.branches[i].parentEle == ele) {
                return this.branches[i];
            }
        }
    };
    this.show = function() {
        for (var i = 0; i < this.eles.length; i++) {
            this.eles[i].style.display = "block";
        }
    };
    this.hide = function() {
        for (var i = 0; i < this.eles.length; i++) {
            this.eles[i].style.display = "none";
        }
    };
    this.change = function(ele) {
        for (var i = 0; i < this.branches.length; i++) {
            if (this.branches[i].parentEle == ele) {
                this.branches[i].show();
            } else {
                this.branches[i].hide();
            }
            this.branches[i].deactiveAll();
        }
    };
    this.active = function(ele) {
        var _f4 = this;
        while (_f4.parent) {
            _f4.change();
            _f4.show();
            _f4 = _f4.parent;
        }
        _f4.deactiveAll();
        ele.addClass(this.options.className);
        this.change(ele);
        if (this.parentEle) {
            $(this.parentEle).addClass(this.options.openedClassName);
        }
    };
    this.deactiveAll = function() {
        this.traversal(function(_f5) {
            for (var i = 0; i < _f5.eles.length; i++) {
                $(_f5.eles[i]).delClass(_f5.options.className);
                $(_f5.eles[i]).delClass(_f5.options.openedClassName);
            }
        });
    };
    this.bind = function(ele) {
        ele = $(ele);
        ele.addEvent("click", function(e) {
            if (!this.options.checkEvent(e)) {
                return;
            }
            this.active(ele);
        }.bind(this));
    };
    this.initialize = function(_f9) {
        this.__parseoptions(_f9);
        if (this.options.autoActive) {
            for (var i = 0; i < this.eles.length; i++) {
                this.bind(this.eles[i]);
            }
        }
    };
    this.__parseoptions = function(_fb) {
        if (!_fb) {
            return;
        }
        for (var i in _fb) {
            if (_fb.hasOwnProperty(i) && this.options[i] != undefined) {
                this.options[i] = _fb[i];
            }
        }
    };
}).call(TreeView.prototype);
object.use("dom, xn.asyncHTML.manager", function(_fd, dom, xn) {
    window.asyncHTMLManager = new xn.asyncHTML.manager.AsyncHTMLManager();
    HTML5History.bind(window.history, {fireEvent:function(ele, type) {
        dom.Element.prototype.fireEvent.call(ele, type);
    },blankHTML:"/ajaxproxy.htm"});
    dom.wrap(window);
    window.addEvent("popstate", function(_102) {
        asyncHTMLManager.listenHash(location.hash.substring(1));
    });
    dom.ready(function() {
        window.asyncHTMLManager.bind(document);
    });
    dom.ready(function() {
        var $$ = Sizzle;
        var _104 = $$(".site-menu-nav .nav-item .item-title").concat($$(".site-menu-apps .with-menu .item-title"));
        var _105 = new TreeView(_104, {hideBranches:true,autoActive:false});
        for (var i = 0; i < _104.length; i++) {
            (function(menu) {
                var _108 = $$("ul li", menu.parentNode);
                if (!_108.length) {
                    return;
                }
                _105.addBranch(menu, _108, {autoActive:false,eventCheck:function(e) {
                    if (e.srcElement.tagName.toUpperCase() != "A") {
                        return false;
                    }
                    return true;
                }});
            })(_104[i]);
        }
        var _10a = false;
        var _10b = function(href) {
            _105.traversal(function(_10d) {
                for (var i = 0; i < _10d.eles.length; i++) {
                    var _10f = $$("a", _10d.eles[i])[0];
                    if (href.host.toLowerCase() == _10f.host.toLowerCase() && href.pathname.toLowerCase() == _10f.pathname.toLowerCase()) {
                        if (_105.activedEle) {
                            $(_105.activedEle).delClass("loading");
                        }
                        $(_10d.eles[i]).addClass("loading");
                        _105.activedBranch = _10d;
                        _105.activedEle = _10d.eles[i];
                    }
                }
            });
        };
        var func = function(e) {
            _10a = true;
            _10b(window.asyncHTMLManager.location);
            document.documentElement.style.cursor = "progress";
            XN.ui.clearDialog();
        };
        var _112 = function() {
            var link = dom.getElement("link[rel=index]");
            if (link) {
                var a = document.createElement("a");
                a.href = link.href;
                _10b(a);
            }
            if (_105.activedEle) {
                var _115 = $("site-menu-apps-more");
                var _116 = $("site-menu-apps-less");
                var list = $("site-menu-apps-all");
                if (Sizzle(".loading", list).length > 0) {
                    _115.hide();
                    _116.show();
                    list.show();
                }
                $(_105.activedEle).delClass("loading");
            }
            if (_105.activedBranch) {
                _105.activedBranch.active(_105.activedEle);
            }
            document.documentElement.style.cursor = "";
        };
        var _118 = function() {
            COMSCORE.beacon({c1:2,c2:6934070,c3:"",c4:"",c5:"",c6:"",c15:""});
        };
        window.asyncHTMLManager.addEvent("beforeload", func);
        window.asyncHTMLManager.addEvent("load", _112);
        window.asyncHTMLManager.addEvent("load", _118);
        window.asyncHTMLManager.addEvent("error", _112);
        window.asyncHTMLManager.addEvent("error", function(_119) {
            if (!_119 || _119.number == 10) {
                $("content").innerHTML = "<h2>\u9875\u9762\u51fa\u9519\u4e86 ^^\uff0c\u8bf7\u5c1d\u8bd5\u5237\u65b0\u9875\u9762(Ctrl+F5)\uff0c\u5982\u679c\u8fd8\u4e0d\u884c\uff0c\u8bf7<a href=\"http://support.renren.com/GetGuestbookList.do\">\u8054\u7cfb\u5ba2\u670d\u4eba\u5458</a>\u3002</h2>";
            } else {
                if (_119.number == 1) {
                    location.reload();
                }
            }
        });
        var _11a = $("site-menu-falopub-box");
        var _11b = $("site-menu-falopub-more");
        var _11c = $("site-menu-falopub-less");
        var _11d = $("all-falopub");
        var _11e = Sizzle(".falopub-list", _11a);
        var _11f = Sizzle(".a-falopub", _11a);
        if (_11f && _11f.length == 1) {
            _11f[0].style.display = "";
        }
        if (_11e && _11e.length > 4) {
            _11b.show();
        }
        var _120 = _11e.length <= 8 ? _11e.length : 8;
        if (_11b && _11c) {
            _11b.addEvent("click", function() {
                _11b.hide();
                _11c.show();
                for (var i = 4; i < _120; i++) {
                    _11e[i].style.display = "";
                }
                _11d.show();
            });
            _11c.addEvent("click", function() {
                _11b.show();
                _11c.hide();
                for (var i = 4; i < _120; i++) {
                    _11e[i].style.display = "none";
                }
                _11d.hide();
            });
        }
        var _123 = $("site-menu-apps-more");
        var _124 = $("site-menu-apps-less");
        var list = $("site-menu-apps-all");
        if (_123 && _124) {
            _123.addEvent("click", function() {
                _123.hide();
                _124.show();
                list.show();
            });
            _124.addEvent("click", function() {
                _124.hide();
                _123.show();
                list.hide();
            });
        }
        var _126 = $("site-menu-minigroups-more");
        var _127 = $("site-menu-minigroups-less");
        var _128 = $("site-menu-minigroups-all");
        var _129 = $("all-minigroups-link");
        if (_126 && _127) {
            var _12a = function(ids) {
                new XN.net.xmlhttp({url:"http://notify.renren.com/feed/group_unread",method:"get",data:"gl=" + ids + "&t=" + new Date().getTime(),onSuccess:function(r) {
                    var j = XN.json.parse(r.responseText);
                    var news = j.unread;
                    for (var key in news) {
                        if (!news.hasOwnProperty(key) || news[key] < 1) {
                            continue;
                        }
                        var a = $("mg_" + key);
                        if (Sizzle(".nav-item-count", a).length > 0) {
                            Sizzle(".nav-item-count", a)[0].innerHTML = news[key];
                        } else {
                            var _131 = $element("span");
                            _131.className = "nav-item-count";
                            _131.innerHTML = news[key];
                            a.appendChild(_131);
                        }
                    }
                }});
            };
            var mgs = Sizzle("#site-menu-minigroups-all .with-count");
            var _133 = [];
            for (var i = 0; i < mgs.length; i++) {
                _133.push(mgs[i].id.slice(3));
            }
            var ids = _133.join(",");
            _126.addEvent("click", function() {
                _12a(ids);
                _126.hide();
                _127.show();
                _128.show();
                if (_129) {
                    _129.show();
                }
            });
            _127.addEvent("click", function() {
                _127.hide();
                _126.show();
                _128.hide();
                if (_129) {
                    _129.hide();
                }
            });
        }
        if (!_10a) {
            func();
        }
    });
    if (XN.user.isGuide) {
        dom.ready(function() {
            window.asyncHTMLManager.initPage();
        });
    } else {
        window.asyncHTMLManager.initPage(true);
    }
});
window.rrmusicboxoption = {width:620,height:565,left:200,top:0,url:"box"};
function popupMusicBox() {
    var _135 = "toolbar=no,location=no,directories=no,menubar=no,resizable=yes,status=yes,scrollbars=no,width=" + rrmusicboxoption.width + ",height=" + rrmusicboxoption.height + ",left=" + rrmusicboxoption.left + ",top=" + rrmusicboxoption.top;
    var _136 = window.open("http://music." + XN.env.domain + "/" + rrmusicboxoption.url, "rrMCWin", _135);
    if (_136) {
        _136.focus();
    } else {
        alert("\u4f60\u7684\u6d4f\u89c8\u5668\u62e6\u622a\u4e86\u64ad\u653e\u5668\u7a97\u53e3,\u8bf7\u8bbe\u7f6e!");
        return false;
    }
}
function delHomeApp(_137, _138) {
    XN.event.stop(_137);
    var id = _138;
    new XN.net.xmlhttp({url:"http://apps." + XN.env.domain + "/menu/deleteRecent.do",data:"app_id=" + id,onSuccess:function(r) {
        $("appsItem_" + id).remove();
    },onError:function() {
        XN.DO.showError("\u7f51\u7edc\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", "\u5220\u9664\u5931\u8d25");
    }});
}
XN.dom.ready(function() {
    if (!$("allAppsInvite")) {
        return;
    }
    if ($("showAppInviteTip")) {
        var tip = ["<div id=\"appsInviteTip\">","<div style=\"width:147px;height:34px;padding:8px;border:1px solid #F9B967;background:#FFFBC1;overflow:hidden;\"><p style=\"float:left;width:130px;line-height:1.4em\">\u4f60\u6709",$("allAppsInvite").innerHTML,"\u6761\u5e94\u7528\u8bf7\u6c42\uff0c\u70b9\u51fb\u6570\u5b57\u5904\u7406\u8bf7\u6c42</p><a href=\"javascript:;\" style=\"float:right;\" class=\"x-to-hide\"></a></div>","<div style=\"width:6px;height:9px;margin-left:-5px;margin-top:-32px;background:url(http://a.xnimg.cn/imgpro/arrow/tip-arrow-left-s.png) 0 0 no-repeat;position:relative;\"></div>","</div>"];
        var tipe = $element("div");
        tipe.innerHTML = tip.join("");
        tipe.style.display = "none";
        $("dropmenuHolder").appendChild(tipe);
        var _13d = new XN.ui.fixPositionElement({id:"appsInviteTip",alignWith:"allAppsInvite",alignType:"2-1",offsetY:-17,offsetX:8});
        new XN.net.xmlhttp({url:"http://www." + XN.env.domain + "/ajaxSetMemcache?memKey=appInvite" + XN.user.id + "&memExpiry=21"});
        $(Sizzle("#appsInviteTip .x-to-hide")[0]).addEvent("click", function() {
            _13d.remove();
        });
    }
    $("allAppsInvite").addEvent("click", function(_13e) {
        XN.event.stop(_13e);
        window.open("http://app." + XN.env.domain + "/app/appRequestList?origin=700045");
    });
});
function openMusicBox() {
    var _13f = "toolbar=no,location=no,directories=no,menubar=no,resizable=yes,status=yes,scrollbars=no,width=" + rrmusicboxoption.width + ",height=" + rrmusicboxoption.height + ",left=" + rrmusicboxoption.left + ",top=" + rrmusicboxoption.top;
    var _140 = window.open("http://music." + XN.env.domain + "/" + rrmusicboxoption.url, "rrMCWin", _13f);
    if (_140) {
        _140.focus();
    } else {
        alert("\u4f60\u7684\u6d4f\u89c8\u5668\u62e6\u622a\u4e86\u64ad\u653e\u5668\u7a97\u53e3,\u8bf7\u8bbe\u7f6e!");
        return false;
    }
}
XN.dom.ready(function() {
    if (Sizzle(".minigroups .nav-item").length < 1) {
        return;
    }
    var _141 = function(ids) {
        new XN.net.xmlhttp({url:"http://notify.renren.com/feed/group_unread",method:"get",data:"gl=" + ids + "&t=" + new Date().getTime(),onSuccess:function(r) {
            var j = XN.json.parse(r.responseText);
            var news = j.unread;
            for (var key in news) {
                if (!news.hasOwnProperty(key) || news[key] < 1) {
                    continue;
                }
                var a = $("mg_" + key);
                if (Sizzle(".nav-item-count", a).length > 0) {
                    Sizzle(".nav-item-count", a)[0].innerHTML = news[key];
                } else {
                    var _148 = $element("span");
                    _148.className = "nav-item-count";
                    _148.innerHTML = news[key];
                    a.appendChild(_148);
                }
            }
        }});
    };
    var _149 = function(id) {
        var a = $("mg_" + id);
        if (Sizzle(".nav-item-count", a).length > 0) {
            Sizzle(".nav-item-count", a)[0].innerHTML = parseInt(Sizzle(".nav-item-count", a)[0].innerHTML) + 1;
        } else {
            var _14c = $element("span");
            _14c.className = "nav-item-count";
            _14c.innerHTML = 1;
            a.appendChild(_14c);
        }
    };
    var _14d = function(id, ids) {
        if (ids.indexOf(id) != -1) {
            return id;
        } else {
            return false;
        }
    };
    var mga = Sizzle(".minigroups .nav-item .with-count");
    var _151 = [];
    for (var i = 0; i < mga.length; i++) {
        _151.push(mga[i].id.slice(3));
    }
    var cb = function(c, _155) {
        var mgId = c.toString();
        var id = _14d(mgId, _151);
        if (id) {
            if (_155) {
                _149(id);
            } else {
                _141(id);
            }
        }
    };
    var _158 = function() {
        XN.webpager.addEvent("webpager_notice_got", function(r) {
            var _15a = XN.json.parse(r.content);
            if (_15a.source != "10000-1") {
                return false;
            }
            var c = _15a.minigroup;
            if (_15a.actor == XN.user.id || !c) {
                return false;
            }
            cb(c, true);
        });
        XN.webpager.addEvent("webpager_notify_got", function(r) {
            var _15d = XN.json.parse(r.content);
            if (!(parseInt(_15d.source) >= 146 <= 150 || parseInt(_15d.source) == 193)) {
                return false;
            }
            var c = _15d.minigroup;
            if (_15d.actor == XN.user.id || !c) {
                return false;
            }
            cb(c, true);
        });
        XN.webpager.addEvent("webpager_groupNumber_got", function(r) {
            var _160 = XN.json.parse(r.content),c = _160.minigroup;
            if (_160.actor == XN.user.id) {
                return false;
            }
            cb(c);
        });
    };
    var _162 = function() {
        for (var i = 0; i < _151.length; i++) {
            var news = webpager.getItem("mg_" + _151[i]);
            if (!news || parseInt(news) < 1) {
                continue;
            }
            var a = $("mg_" + _151[i]);
            if (Sizzle(".nav-item-count", a).length > 0) {
                Sizzle(".nav-item-count", a)[0].innerHTML = news;
            } else {
                var _166 = $element("span");
                _166.className = "nav-item-count";
                _166.innerHTML = news;
                a.appendChild(_166);
            }
        }
    };
    if (!XN.webpager) {
        if (!XN.webpagerUIReadyQ) {
            XN.webpagerUIReadyQ = [];
        }
        XN.webpagerUIReadyQ.push(_158);
    } else {
        _158();
    }
});
