/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { VueUserService } from 'app/entities/vue-user/vue-user.service';
import { IVueUser, VueUser } from 'app/shared/model/vue-user.model';

describe('Service Tests', () => {
    describe('VueUser Service', () => {
        let injector: TestBed;
        let service: VueUserService;
        let httpMock: HttpTestingController;
        let elemDefault: IVueUser;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(VueUserService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new VueUser(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                0,
                0,
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        creatTime: currentDate.format(DATE_FORMAT),
                        lockTime: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a VueUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        creatTime: currentDate.format(DATE_FORMAT),
                        lockTime: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creatTime: currentDate,
                        lockTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new VueUser(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a VueUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        password: 'BBBBBB',
                        cname: 'BBBBBB',
                        userPic: 'BBBBBB',
                        address: 'BBBBBB',
                        age: 1,
                        mobile: 1,
                        email: 'BBBBBB',
                        status: 'BBBBBB',
                        creatTime: currentDate.format(DATE_FORMAT),
                        loginNum: 1,
                        errNmu: 1,
                        deptId: 1,
                        creator: 'BBBBBB',
                        lockTime: currentDate.format(DATE_FORMAT),
                        lockReason: 'BBBBBB',
                        description: 'BBBBBB',
                        reserve: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        creatTime: currentDate,
                        lockTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of VueUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        password: 'BBBBBB',
                        cname: 'BBBBBB',
                        userPic: 'BBBBBB',
                        address: 'BBBBBB',
                        age: 1,
                        mobile: 1,
                        email: 'BBBBBB',
                        status: 'BBBBBB',
                        creatTime: currentDate.format(DATE_FORMAT),
                        loginNum: 1,
                        errNmu: 1,
                        deptId: 1,
                        creator: 'BBBBBB',
                        lockTime: currentDate.format(DATE_FORMAT),
                        lockReason: 'BBBBBB',
                        description: 'BBBBBB',
                        reserve: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creatTime: currentDate,
                        lockTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a VueUser', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
