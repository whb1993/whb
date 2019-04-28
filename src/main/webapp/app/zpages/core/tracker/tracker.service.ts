import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Observable, Observer, Subscription } from 'rxjs';

import * as SockJS from 'sockjs-client';
import * as Stomp from 'webstomp-client';
import { AuthServerProvider, CSRFService, WindowRef } from 'app/core';
import { JhiTrackerService } from 'app/core/tracker/tracker.service';

/**
 * 为shell重写websock连接
 * 包括 客户端发送请求 服务器发送信息
 */
@Injectable({ providedIn: 'root' })
export class ZTrackerService {
    public stompClient = null;
    subscriber = null;
    connection: Promise<any>;
    connectedPromise: any;
    listener: Observable<any>;
    listenerObserver: Observer<any>;
    alreadyConnectedOnce = false;

    constructor(private trackerService: JhiTrackerService) {
        this.listener = this.createListener();
    }

    sendMes(mes: string) {
        this.stompClient = this.trackerService.stompClient;
        if (this.stompClient !== null && this.stompClient.connected) {
            this.stompClient.send(
                '/topic/sendMes', // destination
                JSON.stringify({ mes }), // body
                {} // header
            );
        }
    }

    subscribeMes() {
        this.connection = this.trackerService.connection;
        this.stompClient = this.trackerService.stompClient;
        this.connection.then(() => {
            this.subscriber = this.stompClient.subscribe('/topic/reviveMes', data => {
                this.listenerObserver.next(JSON.parse(data.body));
            });
        });
    }
    receive() {
        return this.listener;
    }
    unSubscribeMes() {
        if (this.subscriber !== null) {
            this.subscriber.unsubscribe();
        }
        this.listener = this.createListener();
    }
    private createListener(): Observable<any> {
        return new Observable(observer => {
            this.listenerObserver = observer;
        });
    }
}
