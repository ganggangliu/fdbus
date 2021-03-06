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
import ipc.fdbus.FdbusParcelable;
import ipc.fdbus.FdbusSerializer;
import ipc.fdbus.FdbusDeserializer;

public class CPerson implements FdbusParcelable
{
    public static class CCar implements FdbusParcelable
    {
        public void serialize(FdbusSerializer serializer)
        {
            serializer.inS(mBrand);
            serializer.inS(mModel);
            serializer.in32(mPrice);
        }
        public void deserialize(FdbusDeserializer deserializer)
        {
            mBrand = deserializer.outS();
            mModel = deserializer.outS();
            mPrice = deserializer.out32();
        }
        
        public String mBrand;
        public String mModel;
        public int mPrice;
    }
    public void serialize(FdbusSerializer serializer)
    {
        serializer.inS(mName);
        serializer.in8(mAge);
        serializer.in(mCars);
        serializer.inS(mAddress);
        serializer.in32(mSalary);
    }
    public void deserialize(FdbusDeserializer deserializer)
    {
        mName = deserializer.outS();
        mAge = deserializer.out8();

        mCars = deserializer.out(new CCar[deserializer.arrayLength()], CCar.class);
        mAddress = deserializer.outS();
        mSalary = deserializer.out32();
    }
    
    public String mName;
    public int mAge;
    public int mSalary;
    public String mAddress;
    public CCar[] mCars;
}

