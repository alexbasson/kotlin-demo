import * as React from 'react';
import {Account} from './Account';

interface Props {
  account: Account;
}

export const AccountsCardRow = (props: Props) => {
  const account = props.account;
  return (
    <tr>
      <td>{account.number}</td>
      <td>{account.type}</td>
      <td>{account.fullname}</td>
    </tr>
  )
};
