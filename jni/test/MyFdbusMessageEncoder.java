/*
 * Copyright (C) 2015   Jeremy Chen jeremy_cz@yahoo.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ipc.fdbus.Example;
import ipc.fdbus.FdbusMessageEncoder;
import com.google.protobuf.AbstractMessageLite;
//import com.google.protobuf.nano.MessageNano;
import ipc.fdbus.FdbusSerializer;
import ipc.fdbus.FdbusParcelable;

public class MyFdbusMessageEncoder extends FdbusMessageEncoder
{
    public byte[] serialize(Object msg)
    {
        if (msg instanceof AbstractMessageLite)
        {
       	    return ((AbstractMessageLite) msg).toByteArray();
        }
        else if (msg instanceof FdbusParcelable)
        {
            FdbusSerializer serializer = new FdbusSerializer();
            serializer.in((FdbusParcelable)msg);
            return serializer.export();
        }
        //else if (msg instanceof MessageNano)
        //{
        //    return MessageNano.toByteArray((MessageNano) msg);
        //}
        return null;
    }

    public String toString(Object msg)
    {
        if (msg instanceof AbstractMessageLite)
        {
            return ((AbstractMessageLite) msg).toString();
        }
        else if (msg instanceof FdbusParcelable)
        {
            // not supported.
        }
        //else if (msg instanceof MessageNano)
        //{
        //    return ((MessageNano) msg).toString();
        //}
        return null;
    }
}

