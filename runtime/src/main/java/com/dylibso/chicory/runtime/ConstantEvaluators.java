package com.dylibso.chicory.runtime;

import static com.dylibso.chicory.wasm.types.OpCode.GLOBAL_GET;

import com.dylibso.chicory.wasm.types.Instruction;
import com.dylibso.chicory.wasm.types.Value;
import java.util.Arrays;
import java.util.List;

final class ConstantEvaluators {
    private ConstantEvaluators() {}

    public static long computeConstantValue(Instance instance, Instruction[] expr) {
        return computeConstantValue(instance, Arrays.asList(expr));
    }

    public static long computeConstantValue(Instance instance, List<Instruction> expr) {
        long tos = -1L;
        for (var instruction : expr) {
            switch (instruction.opcode()) {
                case F32_CONST:
                case F64_CONST:
                case I32_CONST:
                case I64_CONST:
                case REF_FUNC:
                    {
                        tos = instruction.operand(0);
                        break;
                    }
                case REF_NULL:
                    {
                        tos = Value.REF_NULL_VALUE;
                        break;
                    }
                case GLOBAL_GET:
                    {
                        var idx = (int) instruction.operand(0);
                        tos = instance.global(idx).getValue();
                        break;
                    }
                case END:
                    {
                        break;
                    }
            }
        }
        return tos;
    }

    public static Instance computeConstantInstance(Instance instance, List<Instruction> expr) {
        for (Instruction instruction : expr) {
            if (instruction.opcode() == GLOBAL_GET) {
                return instance.global((int) instruction.operand(0)).getInstance();
            }
        }
        return instance;
    }
}
