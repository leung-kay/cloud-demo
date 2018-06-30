package com.ruifucredit.cloud.kit.dto;

public enum StatusCode {

    CONTINUE(1000, "继续。客户端应继续其请求"),
    SWITCHING_PROTOCOLS(1010, "切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议"),

    OK(2000, "请求成功"),
    CREATED(2010, "已创建。成功请求并创建了新的资源"),
    ACCEPTED(2020, "已接受。已经接受请求，但未处理完成"),
    NONAUTHORITATIVE_INFORMATION(2030, "非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本"),
    NO_CONTENT(2040, "无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档"),
    RESET_CONTENT(2050, "重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域"),
    PARTIAL_CONTENT(2060, "部分内容。服务器成功处理了部分GET请求"),

    MULTIPLE_CHOICES(3000, "多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择"),
    MOVED_PERMANENTLY(3010, "永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替"),
    FOUND(3020, "临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI"),
    SEE_OTHER(3030, "查看其它地址。与301类似。使用GET和POST请求查看"),
    NOT_MODIFIED(3040, "未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源"),
    USE_PROXY(3050, "使用代理。所请求的资源必须通过代理访问"),
    UNUSED(3060, "已经被废弃的HTTP状态码"),
    TEMPORARY_REDIRECT(3070, "临时重定向。与302类似。使用GET请求重定向"),

    BAD_REQUEST(4000, "请求异常"),
    UNAUTHORIZED(4010, "未认证"),
    PAYMENT_REQUIRED(4020, "未定义请求异常"),
    FORBIDDEN(4030, "拒绝执行"),
    NOT_FOUND(4040, "资源无法找到"),
    METHOD_NOT_ALLOWED(4050, "禁止执行"),
    NOT_ACCEPTABLE(4060, "服务器无法根据客户端请求的内容特性完成请求"),
    PROXY_AUTHENTICATION_REQUIRED(4070, "请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权"),
    REQUEST_TIMEOUT(4080, "服务器等待客户端发送的请求时间过长，超时"),
    CONFLICT(4090, "服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突"),
    GONE(4100, "客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置"),
    LENGTH_REQUIRED(4110, "服务器无法处理客户端发送的不带Content-Length的请求信息"),
    PRECONDITION_FAILED(4120, "客户端请求信息的先决条件错误"),
    REQUEST_ENTITY_TOO_LARGE(4130, "由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息"),
    REQUEST_URI_TOO_LARGE(4140, "请求的URI过长（URI通常为网址），服务器无法处理"),
    UNSUPPORTED_MEDIA_TYPE(4150, "服务器无法处理请求附带的媒体格式"),
    REQUESTED_RANGE_NOT_SATISFIABLE(4160, "客户端请求的范围无效"),
    EXPECTATION_FAILED(4170, "服务器无法满足Expect的请求头信息"),

    INTERNAL_SERVER_ERROR(5000, "服务器内部错误"),
    NOT_IMPLEMENTED(5010, "服务器不支持请求的功能，无法完成请求"),
    BAD_GATEWAY(5020, "充当网关或代理的服务器，从远端服务器接收到了一个无效的请求"),
    SERVICE_UNAVAILABLE(5030, "由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中"),
    GATEWAY_TIMEOUT(5040, "充当网关或代理的服务器，未及时从远端服务器获取请求"),
    HTTP_VERSION_NOT_SUPPORTED(5050, "服务器不支持请求的HTTP协议的版本，无法完成处理");

    public int code;
    public String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String toString() {
        return String.valueOf(code);
    }

}
