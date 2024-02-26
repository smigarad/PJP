def calculate(expression):
    def apply_operator(operators, values):
        operator = operators.pop()
        right = values.pop()
        left = values.pop()
        if operator == '+':
            values.append(left + right)
        elif operator == '-':
            if left < right:
                return "ERROR"
            values.append(left - right)
        elif operator == '*':
            values.append(left * right)
        elif operator == '/':
            values.append(left // right)
        elif operator == '^':
            values.append(left ** right)

    def greater_precedence(op1, op2):
        precedences = {'+': 1, '-': 1, '*': 2, '/': 2, '^': 3}
        return precedences[op1] > precedences[op2]

    operators = []
    values = []
    i = 0
    while i < len(expression):
        if expression[i] == ' ':
            i += 1
            continue
        if expression[i].isdigit():
            j = i
            while j < len(expression) and expression[j].isdigit():
                j += 1
            num = int(expression[i:j])
            if num <= 0:
                return "ERROR"
            values.append(num)
            i = j
        else:
            if expression[i] == '(':
                operators.append(expression[i])
            elif expression[i] == ')':
                while operators and operators[-1] != '(':
                    result = apply_operator(operators, values)
                    if result == "ERROR":
                        return "ERROR"
                operators.pop()
            else:
                while (operators and operators[-1] != '(' and
                       (greater_precedence(operators[-1], expression[i]) or
                        (operators[-1] == '^' and expression[i] == '^'))):
                    result = apply_operator(operators, values)
                    if result == "ERROR":
                        return "ERROR"
                operators.append(expression[i])
            i += 1

    while operators:
        if operators[-1] == '(':
            return "ERROR"
        result = apply_operator(operators, values)
        if result == "ERROR":
            return "ERROR"

    return values[0]

def main():
    N = int(input().strip())
    for _ in range(N):
        expression = input().strip()
        try:
            result = calculate(expression.replace('**', '^'))
            print(expression)
            print(result)
        except Exception as e:
            print("ERROR")

if __name__ == '__main__':
    main()
