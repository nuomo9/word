let ajax = {};
ajax.common = function (url, method, callback, data, headers) {
    if (!url) {
        throw new Error("url无效");
    }
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            let response = null;
            if (request.response !== null && request.response !== "") {
                response = JSON.parse(request.response);
                if (callback) {
                    callback(response);
                }
            }
        }
    };
    request.open(method, url);
    if (headers) {
        for (let name of headers) {
            request.setRequestHeader(name, headers[name]);
        }
    }
    // post和put自动设置content-type请求头
    if ("post" === method.toLowerCase() || "put" === method.toLowerCase()) {
        request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    }
    request.send(data);
};

ajax.get = function (url, callback, data, headers) {
    this.common(url, "get", callback, data, headers);
};
ajax.post = function (url, callback, data, headers) {
    this.common(url, "post", callback, data, headers);
};
ajax.put = function (url, callback, data, headers) {
    this.common(url, "put", callback, data, headers);
};
ajax.delete = function (url, callback, data, headers) {
    this.common(url, "delete", callback, data, headers);
};
