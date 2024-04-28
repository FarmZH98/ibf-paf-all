package sg.edu.nus.iss.day24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24.repo.AccountRepo;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private PlatformTransactionManager txMgr;

    public void fundsTransfer2(String fromAcct, String toAcct, float amount) {
        TransactionStatus txStatus = txMgr.getTransaction(TransactionDefinition.withDefaults());

        try {
            accountRepo.updateBalanceById(fromAcct, -amount);
            accountRepo.updateBalanceById(toAcct, amount);
            txMgr.commit(txStatus);
        } catch (Exception ex) {
            txMgr.rollback(txStatus);
        }
    }

    //rollback if is an unchecked exception
    //won't rollback if checked exeption
    @Transactional(rollbackFor = AccountsException.class) //must specify rollback for, otherwise it won't rollback if AccountsException occur as it is checked exception
    public void fundsTransfer(String fromAcct, String toAcct, float amount) throws AccountsException{
        //Start transaction

        if(!( accountRepo.updateBalanceById(fromAcct, -amount) && accountRepo.updateBalanceById(toAcct, amount))) {
            throw new AccountsException("Cannot perform transfer");
        }

        //commit
    }
}
