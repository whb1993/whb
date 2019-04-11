import { Moment } from 'moment';

export interface IVueUser {
    id?: number;
    name?: string;
    password?: string;
    cname?: string;
    userPic?: string;
    address?: string;
    age?: number;
    mobile?: number;
    email?: string;
    status?: string;
    creatTime?: Moment;
    loginNum?: number;
    errNmu?: number;
    deptId?: number;
    creator?: string;
    lockTime?: Moment;
    lockReason?: string;
    description?: string;
    reserve?: string;
}

export class VueUser implements IVueUser {
    constructor(
        public id?: number,
        public name?: string,
        public password?: string,
        public cname?: string,
        public userPic?: string,
        public address?: string,
        public age?: number,
        public mobile?: number,
        public email?: string,
        public status?: string,
        public creatTime?: Moment,
        public loginNum?: number,
        public errNmu?: number,
        public deptId?: number,
        public creator?: string,
        public lockTime?: Moment,
        public lockReason?: string,
        public description?: string,
        public reserve?: string
    ) {}
}
