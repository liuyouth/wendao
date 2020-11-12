package pub.moi.wendao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

public class JsonRoot
{

    /**
     * actions : [{"global_action_seq":5487467,"account_action_seq":4582001,"block_num":2757950,"block_time":"2018-09-26T17:16:57.000","action_trace":{"receipt":{"receiver":"eosio","act_digest":"0b912f214be7496e64cdb3eb509494cdc18a7265adbaaacb6e6f976832f5465e","global_sequence":5487467,"recv_sequence":4582002,"auth_sequence":[["dillonlts",14]],"code_sequence":1,"abi_sequence":1},"act":{"account":"eosio","name":"transfer","authorization":[{"actor":"dillonlts","permission":"active"}],"data":{"from":"dillonlts","to":"dilloneos","quantity":"0.1000 EOS","memo":"测试"},"hex_data":"0000c0394e1aa34b0000c0544d1aa34be80300000000000004454f530000000006e6b58be8af95"},"elapsed":288,"cpu_usage":0,"console":"","total_cpu_usage":0,"trx_id":"2fdf385a806d0123c3be0ee56f65494bc97cc325fe1616fafa6d44471d38dc0a","inline_traces":[]}},{"global_action_seq":5375004,"account_action_seq":4473341,"block_num":2712498,"block_time":"2018-09-25T03:22:21.000","action_trace":{"receipt":{"receiver":"eosio","act_digest":"ec18c85643bf5604ecfb174aba74a3c67667885667e46131dec280fb3acf11df","global_sequence":5375004,"recv_sequence":4473342,"auth_sequence":[["dilloneos",262]],"code_sequence":1,"abi_sequence":1},"act":{"account":"eosio","name":"newaccount","authorization":[{"actor":"dilloneos","permission":"active"}],"data":"123123213y2udhfafdajq28hakjdfhafa","hex_data":"0000c0544d1aa34b000000005c9a91dbe80300000000000004454f530000000000"},"elapsed":261,"cpu_usage":0,"console":"","total_cpu_usage":0,"trx_id":"8a47291af6dcd33e0fd3240fbfc4a1a355082cdb6275983c2c9129e0e68a0cda","inline_traces":[]}}]
     * last_irreversible_block : 2772205
     */

    private int last_irreversible_block;
    private List<ActionsBean> actions;

    public int getLast_irreversible_block()
    {
        return last_irreversible_block;
    }

    public void setLast_irreversible_block(int last_irreversible_block)
    {
        this.last_irreversible_block = last_irreversible_block;
    }

    public List<ActionsBean> getActions()
    {
        return actions;
    }

    public void setActions(List<ActionsBean> actions)
    {
        this.actions = actions;
    }

    public static class ActionsBean
    {
        /**
         * global_action_seq : 5487467
         * account_action_seq : 4582001
         * block_num : 2757950
         * block_time : 2018-09-26T17:16:57.000
         * action_trace : {"receipt":{"receiver":"eosio","act_digest":"0b912f214be7496e64cdb3eb509494cdc18a7265adbaaacb6e6f976832f5465e","global_sequence":5487467,"recv_sequence":4582002,"auth_sequence":[["dillonlts",14]],"code_sequence":1,"abi_sequence":1},"act":{"account":"eosio","name":"transfer","authorization":[{"actor":"dillonlts","permission":"active"}],"data":{"from":"dillonlts","to":"dilloneos","quantity":"0.1000 EOS","memo":"测试"},"hex_data":"0000c0394e1aa34b0000c0544d1aa34be80300000000000004454f530000000006e6b58be8af95"},"elapsed":288,"cpu_usage":0,"console":"","total_cpu_usage":0,"trx_id":"2fdf385a806d0123c3be0ee56f65494bc97cc325fe1616fafa6d44471d38dc0a","inline_traces":[]}
         */

        private int global_action_seq;
        private int account_action_seq;
        private int block_num;
        private String block_time;
        private ActionTraceBean action_trace;

        public int getGlobal_action_seq()
        {
            return global_action_seq;
        }

        public void setGlobal_action_seq(int global_action_seq)
        {
            this.global_action_seq = global_action_seq;
        }

        public int getAccount_action_seq()
        {
            return account_action_seq;
        }

        public void setAccount_action_seq(int account_action_seq)
        {
            this.account_action_seq = account_action_seq;
        }

        public int getBlock_num()
        {
            return block_num;
        }

        public void setBlock_num(int block_num)
        {
            this.block_num = block_num;
        }

        public String getBlock_time()
        {
            return block_time;
        }

        public void setBlock_time(String block_time)
        {
            this.block_time = block_time;
        }

        public ActionTraceBean getAction_trace()
        {
            return action_trace;
        }

        public void setAction_trace(ActionTraceBean action_trace)
        {
            this.action_trace = action_trace;
        }

        public static class ActionTraceBean
        {
            /**
             * receipt : {"receiver":"eosio","act_digest":"0b912f214be7496e64cdb3eb509494cdc18a7265adbaaacb6e6f976832f5465e","global_sequence":5487467,"recv_sequence":4582002,"auth_sequence":[["dillonlts",14]],"code_sequence":1,"abi_sequence":1}
             * act : {"account":"eosio","name":"transfer","authorization":[{"actor":"dillonlts","permission":"active"}],"data":{"from":"dillonlts","to":"dilloneos","quantity":"0.1000 EOS","memo":"测试"},"hex_data":"0000c0394e1aa34b0000c0544d1aa34be80300000000000004454f530000000006e6b58be8af95"}
             * elapsed : 288
             * cpu_usage : 0
             * console :
             * total_cpu_usage : 0
             * trx_id : 2fdf385a806d0123c3be0ee56f65494bc97cc325fe1616fafa6d44471d38dc0a
             * inline_traces : []
             */

            private ReceiptBean receipt;
            private ActBean act;
            private int elapsed;
            private int cpu_usage;
            private String console;
            private int total_cpu_usage;
            private String trx_id;
            private List<?> inline_traces;

            public ReceiptBean getReceipt()
            {
                return receipt;
            }

            public void setReceipt(ReceiptBean receipt)
            {
                this.receipt = receipt;
            }

            public ActBean getAct()
            {
                return act;
            }

            public void setAct(ActBean act)
            {
                this.act = act;
            }

            public int getElapsed()
            {
                return elapsed;
            }

            public void setElapsed(int elapsed)
            {
                this.elapsed = elapsed;
            }

            public int getCpu_usage()
            {
                return cpu_usage;
            }

            public void setCpu_usage(int cpu_usage)
            {
                this.cpu_usage = cpu_usage;
            }

            public String getConsole()
            {
                return console;
            }

            public void setConsole(String console)
            {
                this.console = console;
            }

            public int getTotal_cpu_usage()
            {
                return total_cpu_usage;
            }

            public void setTotal_cpu_usage(int total_cpu_usage)
            {
                this.total_cpu_usage = total_cpu_usage;
            }

            public String getTrx_id()
            {
                return trx_id;
            }

            public void setTrx_id(String trx_id)
            {
                this.trx_id = trx_id;
            }

            public List<?> getInline_traces()
            {
                return inline_traces;
            }

            public void setInline_traces(List<?> inline_traces)
            {
                this.inline_traces = inline_traces;
            }

            public static class ReceiptBean
            {
                /**
                 * receiver : eosio
                 * act_digest : 0b912f214be7496e64cdb3eb509494cdc18a7265adbaaacb6e6f976832f5465e
                 * global_sequence : 5487467
                 * recv_sequence : 4582002
                 * auth_sequence : [["dillonlts",14]]
                 * code_sequence : 1
                 * abi_sequence : 1
                 */

                private String receiver;
                private String act_digest;
                private int global_sequence;
                private int recv_sequence;
                private int code_sequence;
                private int abi_sequence;
                private List<List<String>> auth_sequence;

                public String getReceiver()
                {
                    return receiver;
                }

                public void setReceiver(String receiver)
                {
                    this.receiver = receiver;
                }

                public String getAct_digest()
                {
                    return act_digest;
                }

                public void setAct_digest(String act_digest)
                {
                    this.act_digest = act_digest;
                }

                public int getGlobal_sequence()
                {
                    return global_sequence;
                }

                public void setGlobal_sequence(int global_sequence)
                {
                    this.global_sequence = global_sequence;
                }

                public int getRecv_sequence()
                {
                    return recv_sequence;
                }

                public void setRecv_sequence(int recv_sequence)
                {
                    this.recv_sequence = recv_sequence;
                }

                public int getCode_sequence()
                {
                    return code_sequence;
                }

                public void setCode_sequence(int code_sequence)
                {
                    this.code_sequence = code_sequence;
                }

                public int getAbi_sequence()
                {
                    return abi_sequence;
                }

                public void setAbi_sequence(int abi_sequence)
                {
                    this.abi_sequence = abi_sequence;
                }

                public List<List<String>> getAuth_sequence()
                {
                    return auth_sequence;
                }

                public void setAuth_sequence(List<List<String>> auth_sequence)
                {
                    this.auth_sequence = auth_sequence;
                }
            }

            public static class ActBean
            {
                /**
                 * account : eosio
                 * name : transfer
                 * authorization : [{"actor":"dillonlts","permission":"active"}]
                 * data : {"from":"dillonlts","to":"dilloneos","quantity":"0.1000 EOS","memo":"测试"}
                 * hex_data : 0000c0394e1aa34b0000c0544d1aa34be80300000000000004454f530000000006e6b58be8af95
                 */

                private String account;
                private String name;
                private String data;
                private String hex_data;
                private List<AuthorizationBean> authorization;

                public String getAccount()
                {
                    return account;
                }

                public void setAccount(String account)
                {
                    this.account = account;
                }

                public String getName()
                {
                    return name;
                }

                public void setName(String name)
                {
                    this.name = name;
                }

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }

                public String getHex_data()
                {
                    return hex_data;
                }

                public void setHex_data(String hex_data)
                {
                    this.hex_data = hex_data;
                }

                public List<AuthorizationBean> getAuthorization()
                {
                    return authorization;
                }

                public void setAuthorization(List<AuthorizationBean> authorization)
                {
                    this.authorization = authorization;
                }

                public DataBean getDataBean(){
                    DataBean dataBean = null;
                    if (isJSONValid(getData()))
                        dataBean =  new Gson().fromJson(getData(),DataBean.class);
                    return dataBean;
                }
                // 重点

                public final static boolean isJSONValid(String jsonInString) {
                    return  -1 != jsonInString.indexOf("{");
//                    try {
//                        new Gson().fromJson(jsonInString, Object.class);
//                        return true;
//                    } catch(JsonSyntaxException ex) {
//                        return false;
//                    }
                }

                public static class DataBean
                {
                    /**
                     * from : dillonlts
                     * to : dilloneos
                     * quantity : 0.1000 EOS
                     * memo : 测试
                     */

                    private String from;
                    private String to;
                    private String quantity;
                    private String memo;

                    public String getFrom()
                    {
                        return from;
                    }

                    public void setFrom(String from)
                    {
                        this.from = from;
                    }

                    public String getTo()
                    {
                        return to;
                    }

                    public void setTo(String to)
                    {
                        this.to = to;
                    }

                    public String getQuantity()
                    {
                        return quantity;
                    }

                    public void setQuantity(String quantity)
                    {
                        this.quantity = quantity;
                    }

                    public String getMemo()
                    {
                        return memo;
                    }

                    public void setMemo(String memo)
                    {
                        this.memo = memo;
                    }

                    @Override
                    public String toString() {
                        return "DataBean{" +
                                "from='" + from + '\'' +
                                ", to='" + to + '\'' +
                                ", quantity='" + quantity + '\'' +
                                ", memo='" + memo + '\'' +
                                '}';
                    }
                }

                public static class AuthorizationBean
                {
                    /**
                     * actor : dillonlts
                     * permission : active
                     */

                    private String actor;
                    private String permission;

                    public String getActor()
                    {
                        return actor;
                    }

                    public void setActor(String actor)
                    {
                        this.actor = actor;
                    }

                    public String getPermission()
                    {
                        return permission;
                    }

                    public void setPermission(String permission)
                    {
                        this.permission = permission;
                    }
                }
            }
        }
    }


    public static class PriceModel {
        private String vip1;
        private String vip2;
        private String noVip;

        public String getVip1() {
            return vip1;
        }

        public void setVip1(String vip1) {
            this.vip1 = vip1;
        }

        public String getVip2() {
            return vip2;
        }

        public void setVip2(String vip2) {
            this.vip2 = vip2;
        }

        public String getNoVip() {
            return noVip;
        }

        public void setNoVip(String noVip) {
            this.noVip = noVip;
        }
    }

}
