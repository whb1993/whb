/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WhbTestModule } from '../../../test.module';
import { BestUserDetailComponent } from 'app/entities/best-user/best-user-detail.component';
import { BestUser } from 'app/shared/model/best-user.model';

describe('Component Tests', () => {
    describe('BestUser Management Detail Component', () => {
        let comp: BestUserDetailComponent;
        let fixture: ComponentFixture<BestUserDetailComponent>;
        const route = ({ data: of({ bestUser: new BestUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [BestUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BestUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BestUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bestUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
