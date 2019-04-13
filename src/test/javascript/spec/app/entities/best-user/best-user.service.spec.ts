/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BestUserService } from 'app/entities/best-user/best-user.service';
import { IBestUser, BestUser } from 'app/shared/model/best-user.model';

describe('Service Tests', () => {
    describe('BestUser Service', () => {
        let injector: TestBed;
        let service: BestUserService;
        let httpMock: HttpTestingController;
        let elemDefault: IBestUser;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BestUserService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new BestUser(
                0,
                'AAAAAAA',
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a BestUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new BestUser(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a BestUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        userName: 'BBBBBB',
                        value1: 1,
                        value2: 1,
                        value3: 1,
                        value4: 1,
                        value5: 1,
                        value6: 1,
                        value7: 1,
                        value8: 1,
                        value9: 1,
                        value10: 1,
                        value11: 1,
                        value12: 1,
                        yuanyin: 'BBBBBB',
                        des1: 'BBBBBB',
                        des2: 'BBBBBB',
                        des3: 'BBBBBB',
                        des4: 'BBBBBB',
                        des5: 'BBBBBB',
                        des6: 'BBBBBB',
                        des7: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of BestUser', async () => {
                const returnedFromService = Object.assign(
                    {
                        userName: 'BBBBBB',
                        value1: 1,
                        value2: 1,
                        value3: 1,
                        value4: 1,
                        value5: 1,
                        value6: 1,
                        value7: 1,
                        value8: 1,
                        value9: 1,
                        value10: 1,
                        value11: 1,
                        value12: 1,
                        yuanyin: 'BBBBBB',
                        des1: 'BBBBBB',
                        des2: 'BBBBBB',
                        des3: 'BBBBBB',
                        des4: 'BBBBBB',
                        des5: 'BBBBBB',
                        des6: 'BBBBBB',
                        des7: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a BestUser', async () => {
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
