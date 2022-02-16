package br.com.lab.impacta.account.domain.service;

import br.com.lab.impacta.account.domain.model.Account;

//Uma interface está para Ports na arquitetura hexagonal
public interface AccountService {

    Account find(Long accountId);

    void debit(Long accountId, Double valueOfDebit);
}
