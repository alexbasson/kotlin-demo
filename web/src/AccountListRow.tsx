import * as React from 'react';
import {Account} from './Account';

interface Props {
    account: Account;
}

export const AccountListRow = (props: Props) => {
    const account = props.account;
    return (
        <li>
            {account.number} {account.type} {account.fullname}
        </li>
    )
};
