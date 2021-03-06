/*
 * Copyright 2017 Sam Sun <github-contact@samczsun.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heliosdecompiler.helios.controller.transformers.decompilers;

import com.google.inject.Singleton;
import com.heliosdecompiler.helios.controller.configuration.ConfigurationSerializer;
import com.heliosdecompiler.helios.controller.configuration.IntegerSetting;
import com.heliosdecompiler.helios.controller.configuration.Setting;
import com.heliosdecompiler.transformerapi.StandardTransformers;
import com.heliosdecompiler.transformerapi.decompilers.fernflower.FernflowerSettings;

@Singleton
public class FernflowerDecompilerController extends DecompilerController<FernflowerSettings> {

    public FernflowerDecompilerController() {
        super("Fernflower Decompiler", "fernflower", StandardTransformers.Decompilers.FERNFLOWER);
    }

    @Override
    protected FernflowerSettings defaultSettings() {
        return getDecompiler().defaultSettings();
    }

    @Override
    protected void registerSettings() {
        registerSetting(Boolean.class, new RawBooleanSetting("rbr", "Hide bridge methods", true));
        registerSetting(Boolean.class, new RawBooleanSetting("rsy", "Hide synthetic class members", false));
        registerSetting(Boolean.class, new RawBooleanSetting("din", "Decompile inner classes", true));
        registerSetting(Boolean.class, new RawBooleanSetting("dc4", "Collapse 1.4 class references", true));
        registerSetting(Boolean.class, new RawBooleanSetting("das", "Decompile assertions", true));
        registerSetting(Boolean.class, new RawBooleanSetting("hes", "Hide empty super invocation", true));
        registerSetting(Boolean.class, new RawBooleanSetting("hdc", "Hide empty default constructor", true));
        registerSetting(Boolean.class, new RawBooleanSetting("dgs", "Decompile generic signatures", false));
        registerSetting(Boolean.class, new RawBooleanSetting("ner", "Assume return not throwing exceptions", true));
        registerSetting(Boolean.class, new RawBooleanSetting("den", "Decompile enumerations", true));
        registerSetting(Boolean.class, new RawBooleanSetting("rgn", "Remove getClass() invocation, when it is a part of a qualified new statement", true));
        registerSetting(Boolean.class, new RawBooleanSetting("lit", "Output numeric literals \"as-is\"", false));
        registerSetting(Boolean.class, new RawBooleanSetting("bto", "Interpret int 1 as boolean true (workaround to a compiler bug)", true));
        registerSetting(Boolean.class, new RawBooleanSetting("asc", "Encode non-ASCII characters in string and character literals as Unicode escapes", false));
        registerSetting(Boolean.class, new RawBooleanSetting("nns", "Allow for not set synthetic attribute (workaround to a compiler bug)", true));
        registerSetting(Boolean.class, new RawBooleanSetting("uto", "Consider nameless types as java.lang.Object (workaround to a compiler architecture flaw)", true));
        registerSetting(Boolean.class, new RawBooleanSetting("udv", "Reconstruct variable names from debug information, if present", true));
        registerSetting(Boolean.class, new RawBooleanSetting("rer", "Remove empty exception ranges", true));
        registerSetting(Boolean.class, new RawBooleanSetting("fdi", "De-inline finally structures", true));
        registerSetting(Boolean.class, new RawBooleanSetting("inn", "Check for IntelliJ IDEA-specific @NotNull annotation and remove inserted code if found", true));
        registerSetting(Boolean.class, new RawBooleanSetting("lac", "Decompile lambda expressions to anonymous classes", false));
        registerSetting(Boolean.class, new RawBooleanSetting("bsm", "Display line numbers", true));
//        registerSetting(Boolean.class, new RawBooleanSetting("log", "", true));
        registerSetting(Integer.class, new RawIntegerSetting("mpm", "Maximum allowed processing time per decompiled method, in seconds. 0 means no upper limit", 0, 0, Integer.MAX_VALUE, 1));
        registerSetting(Boolean.class, new RawBooleanSetting("ren", "Rename ambiguous (resp. obfuscated) classes and class elements", false));
//        registerSetting(Boolean.class, new RawBooleanSetting("urc", "", true));
//        registerSetting(Boolean.class, new RawBooleanSetting("nls", "", true));
//        registerSetting(Boolean.class, new RawBooleanSetting("ind", "Indentation string (default is \" \" (3 spaces))", true));
//        registerSetting(Boolean.class, new RawBooleanSetting("ban", "", true));
    }

    private class RawBooleanSetting extends Setting<Boolean, FernflowerSettings> {

        RawBooleanSetting(String id, String desc, boolean defaultValue) {
            super(Boolean.class, defaultValue, ConfigurationSerializer.BOOLEAN, id, desc);
        }

        @Override
        public void apply(FernflowerSettings settings, Boolean value) {
            settings.set(getId(), value ? "1" : "0");
        }
    }

    private class RawIntegerSetting extends IntegerSetting<FernflowerSettings> {

        RawIntegerSetting(String id, String desc, int defaultValue, int min, int max, int step) {
            super(Integer.class, defaultValue, ConfigurationSerializer.INTEGER, id, desc, min, max, step);
        }

        @Override
        public void apply(FernflowerSettings settings, Integer value) {
            settings.set(getId(), value);
        }
    }
}
