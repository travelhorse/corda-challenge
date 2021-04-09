package net.corda.samples.supplychain.accountUtilities;

import com.r3.corda.lib.accounts.contracts.states.AccountInfo;
import com.r3.corda.lib.accounts.workflows.services.AccountService;
import com.r3.corda.lib.accounts.workflows.services.KeyManagementBackedAccountService;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.flows.StartableByService;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.samples.supplychain.states.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StartableByRPC
@StartableByService
public class ViewInboxByAccount extends FlowLogic<List<String>>{

    private final String acctName;

    public ViewInboxByAccount(String acctname) {
        this.acctName = acctname;
    }

    @Override
    public List<String> call() throws FlowException {

        AccountService accountService = getServiceHub().cordaService(KeyManagementBackedAccountService.class);
        AccountInfo myAccount = accountService.accountInfo(acctName).get(0).getState().getData();
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria()
                .withExternalIds(Arrays.asList(myAccount.getIdentifier().getId()));

        List<String> OrderMessages = getServiceHub().getVaultService().queryBy(OrderMessageState.class,criteria).getStates().stream().map(
                it -> "\nOrderMessages State : " + it.getState().getData().getTask()).collect(Collectors.toList());

        List<String> payments = getServiceHub().getVaultService().queryBy(PaymentState.class,criteria).getStates().stream().map(
                it -> "\nPayment State : " +it.getState().getData().getAmount()).collect(Collectors.toList());

        List<String> Parcels = getServiceHub().getVaultService().queryBy(ParcelState.class,criteria).getStates().stream().map(
                it -> "\nParcel State : " + it.getState().getData().getParcel()).collect(Collectors.toList());

        List<String> invoices = getServiceHub().getVaultService().queryBy(InvoiceState.class,criteria).getStates().stream().map(
                it -> "\nInvoice State : " + it.getState().getData().getAmount()).collect(Collectors.toList());

        List<String> deliveryRequest = getServiceHub().getVaultService().queryBy(DeliveryRequestState.class,criteria).getStates().stream().map(
                it -> "\ndeliveryRequest State : " + it.getState().getData().getParcel()).collect(Collectors.toList());

        return Stream.of(OrderMessages, payments, Parcels,invoices,deliveryRequest).flatMap(Collection::stream).collect(Collectors.toList());
    }
}