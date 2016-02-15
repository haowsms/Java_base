/**
 * @author wanghao
 *1. 采用阻塞模式，用线程池中的工作线程处理每个客户链接
 * ServerSocketChannel和SocketChannel采用默认的阻塞模式，为了同时处理多个客户端的链接，必须使用多线程。
 */
package com.base.nio.blocking;