import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBestUser } from 'app/shared/model/best-user.model';

@Component({
    selector: 'jhi-best-user-detail',
    templateUrl: './best-user-detail.component.html'
})
export class BestUserDetailComponent implements OnInit {
    bestUser: IBestUser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bestUser }) => {
            this.bestUser = bestUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
