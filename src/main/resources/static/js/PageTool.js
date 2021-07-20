class PageTool {
    constructor(service) {
        // 在service方法中请求后端分页数据
        this.service = service;
        // 每页条数下拉列表
        this.sizeList = [10, 20, 50, 100, 500];
        // 每页条数,默认下拉框第一条
        this.size = this.sizeList[0];
        // 当前页数,默认为1
        this.current = 1;
        // 总页数
        this.pages = 0;
        // 总条数
        this.total = 0;
        // 维护节点,用于节点状态更新
        this.sizeNode = null;
        this.firstPageNode = null;
        this.previousPageNode = null;
        this.currentPageNode = null;
        this.totalPageNode = null;
        this.nextPageNode = null;
        this.lastPageNode = null;
        this.refreshNode = null;
        this.totalNode = null;
    }

    // 手动设置每页条数下拉列表
    setSizeList() {
        // 从arguments数组中设置参数
        if (arguments.length > 0) {
            this.sizeList = arguments;
            this.size = this.sizeList[0];
        }
        return this;
    }

    // 生成工具栏,返回一个div节点,div包含了"每页数,首页,上一页,当前页数/总页数,下一页,尾页,刷新,总条数"
    generateToolbar() {
        // 空格
        let space = null;
        let obj = this;
        // div父节点
        let div = document.createElement("div");
        // 每页条数下拉列表
        obj.sizeNode = document.createElement("select");
        obj.sizeNode.style = "width: 80px; height: 40px; font-size: 120%; position: relative; left: 0px; top: 5px";
        for (let size of obj.sizeList) {
            let option = document.createElement("option");
            option.value = size;
            option.innerText = size;
            obj.sizeNode.appendChild(option);
        }
        div.appendChild(obj.sizeNode);
        // 首页
        obj.firstPageNode = document.createElement("button");
        obj.firstPageNode.style = "width: 100px; height: 40px; position: relative; left: 20px; top: 5px";
        obj.firstPageNode.innerHTML = '<span style="font-family: 楷体; font-size: 160%; font-weight: bold">首页</span>';
        div.appendChild(obj.firstPageNode);
        // 上一页
        obj.previousPageNode = document.createElement("button");
        obj.previousPageNode.style = "width: 100px; height: 40px; position: relative; left: 25px; top: 5px";
        obj.previousPageNode.innerHTML = '<span style="font-family: 楷体; font-size: 160%; font-weight: bold">上一页</span>';
        div.appendChild(obj.previousPageNode);
        // 当前页数
        obj.currentPageNode = document.createElement("input");
        obj.currentPageNode.type = "text";
        obj.currentPageNode.style = "width: 60px; height: 34px; font-size: 120%; text-align: center; position: relative; left: 30px; top: 4px";
        div.appendChild(obj.currentPageNode);
        // 斜线
        let slash = document.createElement("span");
        slash.style = "font-family: 楷体; font-size: 120%; font-weight: bold; text-align: center; position: relative; left: 30px; top: 4px";
        slash.innerText = "/";
        div.appendChild(slash);
        // 总页数
        obj.totalPageNode = document.createElement("input");
        obj.totalPageNode.type = "text";
        obj.totalPageNode.disabled = true;
        obj.totalPageNode.style = "width: 60px; height: 34px; font-size: 120%; text-align: center; position: relative; left: 30px; top: 4px";
        div.appendChild(obj.totalPageNode);
        // 下一页
        obj.nextPageNode = document.createElement("button");
        obj.nextPageNode.style = "width: 100px; height: 40px; position: relative; left: 35px; top: 5px";
        obj.nextPageNode.innerHTML = '<span style="font-family: 楷体; font-size: 160%; font-weight: bold">下一页</span>';
        div.appendChild(obj.nextPageNode);
        // 尾页
        obj.lastPageNode = document.createElement("button");
        obj.lastPageNode.style = "width: 100px; height: 40px; position: relative; left: 40px; top: 5px";
        obj.lastPageNode.innerHTML = '<span style="font-family: 楷体; font-size: 160%; font-weight: bold">尾页</span>';
        div.appendChild(obj.lastPageNode);
        // 刷新
        obj.refreshNode = document.createElement("button");
        obj.refreshNode.style = "width: 100px; height: 40px; position: relative; left: 45px; top: 5px";
        obj.refreshNode.innerHTML = '<span style="font-family: 楷体; font-size: 160%; font-weight: bold">刷新</span>';
        div.appendChild(obj.refreshNode);
        // 总条数
        let statement = document.createElement("span");
        statement.style = "font-family: 楷体; font-size: 130%; text-align: center; position: relative; left: 65px; top: 5px";
        statement.innerText = "总条数:";
        div.appendChild(statement);
        obj.totalNode = document.createElement("input");
        obj.totalNode.type = "text";
        obj.totalNode.disabled = true;
        obj.totalNode.style = "width: 60px; height: 34px; font-size: 120%; text-align: center; position: relative; left: 70px; top: 4px";
        div.appendChild(obj.totalNode);
        // 事件委托
        div.onclick = function (event) {
            if (event.target === obj.firstPageNode || event.target === obj.firstPageNode.firstElementChild) {
                if (obj.current > 1) {
                    obj.current = 1;
                    obj.service();
                }
            }
            if (event.target === obj.previousPageNode || event.target === obj.previousPageNode.firstElementChild) {
                if (obj.current > 1) {
                    obj.current -= 1;
                    obj.service();
                }
            }
            if (event.target === obj.nextPageNode || event.target === obj.nextPageNode.firstElementChild) {
                if (obj.current < obj.pages) {
                    obj.current += 1;
                    obj.service();
                }
            }
            if (event.target === obj.lastPageNode || event.target === obj.lastPageNode.firstElementChild) {
                if (obj.current < obj.pages) {
                    obj.current = obj.pages;
                    obj.service();
                }
            }
            if (event.target === obj.refreshNode || event.target === obj.refreshNode.firstElementChild) {
                obj.service();
            }
        };
        div.onchange = function (event) {
            // 下拉框条数更新
            if (event.target === obj.sizeNode) {
                obj.size = obj.sizeNode.value;
                // 重新从首页开始
                obj.current = 1;
                obj.service();
            }
            // 当前页数更新
            if (event.target === obj.currentPageNode) {
                let currentStr = obj.currentPageNode.value;
                // 正则校验输入是否正确
                let reg = /^\d+$/;
                if (reg.test(currentStr)) {
                    let current = parseInt(currentStr);
                    if (current > 0 && current <= obj.pages && current !== obj.current) {
                        obj.current = current;
                        obj.service();
                    }
                }
            }
        };
        // 获取初始数据
        obj.service();
        // 返回工具栏父标签
        return div;
    }

    // 重设工具栏参数
    reset() {
        // 每页条数,默认下拉框第一条
        this.size = this.sizeList[0];
        this.sizeNode.value = this.size;
        // 当前页数,默认为1
        this.current = 1;
        // 总页数
        this.pages = 0;
        // 总条数
        this.total = 0;
    }

    // 根据返回结果更新节点状态
    updateNodeStatus(page) {
        let obj = this;
        if (page === null || page.pages < 1) {
            // 数据无效时设置当前页和最大页数为1
            obj.current = 1;
            obj.pages = 1;
            obj.total = 0;
        } else {
            if (page.current < 1) {
                obj.current = 1;
            } else if (page.current > page.pages) {
                // 如果返回的当前页数比返回的总数还要大,则纠正回总数
                obj.current = page.pages;
            }
            obj.pages = page.pages;
            obj.total = page.total;
        }
        // 更新value,设置节点属性,是否可点击等
        obj.currentPageNode.value = obj.current;
        obj.totalPageNode.value = obj.pages;
        obj.totalNode.value = obj.total;
    }

    // service的简单实现
    simpleService(url, callback) {
        let obj = this;
        let request = new XMLHttpRequest();
        // 注意: 事件方法中的this不是指向当前类的对象
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                let response = null;
                let page = null;
                if (request.response !== null && request.response !== "") {
                    // 返回结果转换为json对象
                    response = JSON.parse(request.response);
                    page = response.data;
                    obj.updateNodeStatus(page);
                    callback(page);
                }
            }
        };
        let method = "POST";
        let data = JSON.stringify({
            current: obj.current,
            size: obj.size
        });
        request.open(method, url);
        request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        request.send(data);
    }
}