/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

/**
 * Test the MutablePair class.
 */
public class MutablePairTest {

    @Test
    public void testEmptyArrayLength() {
        @SuppressWarnings("unchecked")
        final MutablePair<Integer, String>[] empty = (MutablePair<Integer, String>[]) MutablePair.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

    @Test
    public void testEmptyArrayGenerics() {
        final MutablePair<Integer, String>[] empty = MutablePair.emptyArray();
        assertEquals(0, empty.length);
    }

    @Test
    public void testBasic() {
        final MutablePair<Integer, String> pair = new MutablePair<>(0, "foo");
        assertEquals(0, pair.getLeft().intValue());
        assertEquals("foo", pair.getRight());
        final MutablePair<Object, String> pair2 = new MutablePair<>(null, "bar");
        assertNull(pair2.getLeft());
        assertEquals("bar", pair2.getRight());
    }

    @Test
    public void testDefault() {
        final MutablePair<Integer, String> pair = new MutablePair<>();
        assertNull(pair.getLeft());
        assertNull(pair.getRight());
    }

    @Test
    public void testEquals() {
        assertEquals(MutablePair.of(null, "foo"), MutablePair.of(null, "foo"));
        assertNotEquals(MutablePair.of("foo", 0), MutablePair.of("foo", null));
        assertNotEquals(MutablePair.of("foo", "bar"), MutablePair.of("xyz", "bar"));

        final MutablePair<String, String> p = MutablePair.of("foo", "bar");
        assertEquals(p, p);
        assertNotEquals(p, new Object());
    }

    @Test
    public void testHashCode() {
        assertEquals(MutablePair.of(null, "foo").hashCode(), MutablePair.of(null, "foo").hashCode());
    }

    @Test
    public void testMutate() {
        final MutablePair<Integer, String> pair = new MutablePair<>(0, "foo");
        pair.setLeft(42);
        pair.setRight("bar");
        assertEquals(42, pair.getLeft().intValue());
        assertEquals("bar", pair.getRight());
    }

    @Test
    public void testPairOf() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        assertEquals(0, pair.getLeft().intValue());
        assertEquals("foo", pair.getRight());
        final MutablePair<Object, String> pair2 = MutablePair.of(null, "bar");
        assertNull(pair2.getLeft());
        assertEquals("bar", pair2.getRight());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() throws Exception {
        final MutablePair<Integer, String> origPair = MutablePair.of(0, "foo");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(origPair);
        final MutablePair<Integer, String> deserializedPair = (MutablePair<Integer, String>) new ObjectInputStream(
                new ByteArrayInputStream(baos.toByteArray())).readObject();
        assertEquals(origPair, deserializedPair);
        assertEquals(origPair.hashCode(), deserializedPair.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("(null,null)", MutablePair.of(null, null).toString());
        assertEquals("(null,two)", MutablePair.of(null, "two").toString());
        assertEquals("(one,null)", MutablePair.of("one", null).toString());
        assertEquals("(one,two)", MutablePair.of("one", "two").toString());
    }
}
