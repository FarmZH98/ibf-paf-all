package sg.edu.nus.iss.day24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24.repo.AccountRepo;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepo accountRepo;

    //rollback if is an unchecked exception
    //won't rollback if checked exeption
    @Transactional(rollbackFor = AccountsException.class)
    public void fundsTransfer(String fromAcct, String toAcct, float amount) throws AccountsException{
        //Start transaction

        if(!( accountRepo.updateBalanceById(fromAcct, -amount) && accountRepo.updateBalanceById(toAcct, amount))) {
            throw AccountsException("Cannot perform transfer");
        }

        //commit
    }
}
