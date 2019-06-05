import * as React from 'react';
import {Account} from './Account';
import {CurrencyFormatter} from './CurrencyFormatter';

interface Props {
  account: Account;
}

export const AccountsCardRow = (props: Props) => {
  const account = props.account;
  return (
    <tr>
      <td className="align-left">{account.number}</td>
      <td className="align-left">{account.type}</td>
      <td className="align-left">{account.fullname}</td>
      <td className="align-right">{CurrencyFormatter.format(account.marketValue)}</td>
    </tr>
  )
};
