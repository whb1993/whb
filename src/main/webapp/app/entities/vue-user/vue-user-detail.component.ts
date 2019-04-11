import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVueUser } from 'app/shared/model/vue-user.model';

@Component({
    selector: 'jhi-vue-user-detail',
    templateUrl: './vue-user-detail.component.html'
})
export class VueUserDetailComponent implements OnInit {
    vueUser: IVueUser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vueUser }) => {
            this.vueUser = vueUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
