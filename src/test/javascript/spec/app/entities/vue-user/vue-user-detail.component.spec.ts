/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WhbTestModule } from '../../../test.module';
import { VueUserDetailComponent } from 'app/entities/vue-user/vue-user-detail.component';
import { VueUser } from 'app/shared/model/vue-user.model';

describe('Component Tests', () => {
    describe('VueUser Management Detail Component', () => {
        let comp: VueUserDetailComponent;
        let fixture: ComponentFixture<VueUserDetailComponent>;
        const route = ({ data: of({ vueUser: new VueUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WhbTestModule],
                declarations: [VueUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VueUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VueUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vueUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
