# ATAD 2026/27 · Laboratório 2 — Medir a complexidade

| | |
|---|---|
| **Unidade curricular** | Algoritmos e Tipos Abstratos de Dados |
| **Semana** | 3 (aplica a matéria da semana 2) |
| **Duração** | 1 h 45 min |
| **Meio** | IntelliJ IDEA, JDK 21 (ou o disponível na sala) |
| **Conteúdo de suporte** | Tópicos 5–8: complexidade, notação assimptótica, casos, algoritmos iterativos |
| **Trabalho** | Individual ou em grupo de 2 (à escolha dos estudantes) |

---

## 1. Objetivos

No final deste laboratório deves ser capaz de:

1. **Instrumentar** um método com um contador de operações, sem alterar a sua lógica;
2. Medir o número de operações em função do tamanho da entrada $n$;
3. Distinguir crescimento **linear** de **quadrático** a partir dos dados medidos;
4. Relacionar as medições com a análise assimptótica feita na aula;
5. Identificar **melhor** e **pior caso** e reconhecer algoritmos cujo custo depende dos dados.

---

## 2. O que é fornecido

O ficheiro `src/Analise.java` com:

| Método | O que faz |
| --- | --- |
| `soma(int[] a)` | soma dos elementos |
| `contarPares(int[] a)` | conta os elementos pares |
| `maximo(int[] a)` | maior elemento |
| `temNegativo(int[] a)` | indica se existe um negativo (termina no primeiro) |
| `contaParesIguais(int[] a)` | conta pares de posições com valores iguais |

e utilitários para construir arrays: `crescente(n)`, `aleatorio(n)`, `aleatorio(n, min, max), `comNegativoNoInicio(n)`, `comNegativoNoFim(n)`, `semNegativos(n)`.

**Regra:** não alteres a lógica dos algoritmos. Podes acrescentar contadores — é isso que o laboratório pede.

---

## 3. Plano da sessão

| Parte | Tarefa                                            | Tempo  |
| :---: | ------------------------------------------------- | :----: |
|   1   | Criar o projeto e executar o código fornecido     | 15 min |
|   2   | Instrumentar e medir `soma` e `maximo`            | 20 min |
|   3   | `contaParesIguais`: reconhecer a curva quadrática | 25 min |
|   4   | Casos: `temNegativo` e dependência dos dados      | 25 min |
|   5   | Conclusões e classificação assimptótica           | 20 min |

---

## 4. Parte 1 — Criar o projeto e testar (15 min)

1. `File` → `New` → `Project…`;
2. Escolhe `Java` no lado direito do *wizard*.
3. Configurações (segue esta ordem):
  - **Location**: (cria/escolhe uma pasta para os projetos de ATAD) 
  - **Name**: `atad-lab2`
  - **Build System**: `Maven`
  - **JDK**: Qualquer existente `>= 21`;
  - `Add sample code` desabilitado -> Sem código inicial.  
  - Advanced Settings:
    - **Group**: `pt.unips.atad`
    - **Artifact Id**: `atad-lab2`
4. Cria a classe `Analise` em `src/main/java` e coloca conteúdo de `src/Analise.java` deste repositório.
5. Executa `Analise.main`.

    O resultado tem de ser:

    ```text
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    soma        = 45
    contarPares = 5
    maximo      = 9
    temNegativo = false
    contaParesIguais = 0
    ```

    Se não coincidir, não avances: o problema está no projeto ou na cópia do ficheiro.

6. Troca `crescente(10)` por `aleatorio(10, -5, 5)`. Executa o programa algumas vezes, verificando que os resultados acompanham o array gerado aleatoriamente.

---

## 5. Parte 2 — Instrumentar e medir (20 min)

> [!NOTE]
> Comenta o código existente no main. Vamos instrumentar e medir alguns métodos de seguida, os resultados das invocações não interessarão, mas sim o número de operações efetuadas para diferentes valores de `n`.

### 5.1. Acrescentar o contador

No topo da classe:

```java
static long ops = 0;
```

Dentro do ciclo de `soma`, antes das restantes instruções do corpo:

```java
ops++;
```

### 5.2. Medir (`soma`)


No `main`, para cada tamanho, **repõe o contador antes da chamada**:

```java
System.out.println("n\tops\tops/n");
for (int n = 100; n <= 800; n *= 2) { //100, 200, 400, 800
    int[] a = aleatorio(n);

    ops = 0; // repor contador
    soma(a);
    System.out.println(n + "\t" + ops + "\t" + (double) ops / n);
}
```

### 5.3. Tabela (`soma` + `maximo`)

Instrumenta também `maximo` (`ops++` no início do corpo do ciclo) e preenche:

|  $n$ | `soma` ops | ops $/ n$ | `maximo` ops | ops $/ n$ |
| ---: | ---------: | --------: | -----------: | --------: |
|  100 |            |           |              |           |
|  200 |            |           |              |           |
|  400 |            |           |              |           |
|  800 |            |           |              |           |

<small>

:warning: O objetivo é **contar operações** — uma medida que não depende da máquina. 

</small>

**Responde em `notas.md`:** quando $n$ duplica, quantas vezes cresce `ops`? O que acontece à razão $\frac{ops}{n}$?

> [!TIP]
> 
> Consulte a secção 11 para considerações sobre $\frac{ops}{n}$.

---

## 6. Parte 3 — A curva quadrática (25 min)

Instrumenta `paresIguais`: o contador entra no **ciclo interno**.

|  $n$ | `paresIguais` ops | ops $/ n^2$ |
| ---: | ----------------: | ----------: |
|  100 |                   |             |
|  200 |                   |             |
|  400 |                   |             |
|  800 |                   |             |

**Responde em `notas.md`:**

1. Quando $n$ duplica, quantas vezes cresce `ops`? Compara com a Parte 2.
2. A razão $\frac{ops}{n^2}$ aproxima-se de que valor? Porquê? (Pensa no número de pares de posições.)
3. Se $\frac{ops}{n}$ é constante num caso (Parte 2) e $\frac{ops}{n^2}$ é constante no outro (Parte 3), o que concluis sobre a complexidade $O$ de cada método?

---

## 7. Parte 4 — Casos e dependência dos dados (25 min)

> [!TIP]
> **Casos** refere-se a melhor, médio e pior caso.

### 7.1. `temNegativo` com $n = 1\,000$

Instrumenta `temNegativo` e mede com os três arrays:

| Entrada                     | Iterações |
| --------------------------- | :-------: |
| `comNegativoNoInicio(1000)` |           |
| `comNegativoNoFim(1000)`    |           |
| `semNegativos(1000)`        |           |

**Responde:**

1. Qual é o melhor caso? E o pior?
2. O número de operações depende só de $n$? Justifica.
3. Se o negativo estivesse a meio do array, quantas iterações farias? E se estivesse numa posição **uniformemente distribuída**?

### 7.2. Um algoritmo sem casos

Mede `soma` com três arrays de **conteúdo diferente** mas do mesmo tamanho: crescente, aleatório e com todos os elementos iguais.

**Responde:** o número de operações muda com os dados? O que isso diz sobre os casos de `soma`?

---

## 8. Parte 5 — Conclusões (20 min)

Escreve em `notas.md` uma tabela final:

| Método             | Melhor caso | Pior caso | Depende dos dados? |
| ------------------ | :---------: | :-------: | :----------------: |
| `soma`             |             |           |                    |
| `contarPares`      |             |           |                    |
| `maximo`           |             |           |                    |
| `temNegativo`      |             |           |                    |
| `contaParesIguais` |             |           |                    |

E responde:

1. Que evidência **medida** mostra que `soma` é linear e `contaParesIguais` é quadrático?
2. Porque é que a análise assimptótica ignora as constantes, se elas aparecem nas medições?
3. Com $n = 1\,000\,000$, quantas operações faria `contaParesIguais`? Quanto tempo demoraria a 1 ns por operação?
4. Qual dos cinco métodos escolherias para procurar um negativo num array muito grande? E se soubesses que os negativos, quando existem, estão sempre no início?

---

## 9. Fecho

- [ ] O projeto compila sem erros;
- [ ] As tabelas das Partes 2, 3 e 4 estão preenchidas com valores **medidos**;
- [ ] O `notas.md` tem as respostas e a tabela final;
- [ ] Consegues explicar, em duas frases, porque é que $\frac{ops}{n}$ constante implica $\Theta(n)$.

---

## 10. Erros comuns

| Erro | Efeito | Como detetar |
| --- | --- | --- |
| Não repor `ops = 0` entre chamadas | valores acumulados | a primeira linha já é enorme |
| Contar o próprio `ops++` | desvio de 1 por iteração | a razão deixa de ser exata, mas a ordem mantém-se |
| Instrumentar só o `if` | contagem depende dos dados sem se perceber | comparar com a tabela da Parte 2 |
| Contabilizar $ops/n²$ incorretamente | parece não convergir | não codificamos a expressão corretamente: `(double) ops / (n*n)`
| Usar `nanoTime()` | resultados irreprodutíveis | varia entre execuções |
| Comparar `ops` de métodos diferentes | números sem significado | cada método tem o seu custo por iteração |

---

## 11. Considerações sobre `ops` $/ n$

A razão $\frac{ops(n)}{n}$ é particularmente interessante na análise de algoritmos porque permite perceber como cresce o número médio de operações por elemento de entrada.

### Relação com a complexidade assintótica

O limite

$$
\lim_{n\to\infty}\frac{ops(n)}{n}
$$

pode ajudar a distinguir algoritmos com crescimento linear dos que crescem mais rapidamente ou mais lentamente:

| Resultado do limite | Interpretação                 |
| ------------------- | ----------------------------- |
| $0$                 | Crescimento inferior a linear |
| Constante positiva  | Crescimento linear            |
| $+\infty$           | Crescimento superior a linear |

### `ops` $/ n^2$

O rácio $\frac{ops(n)}{n^2}$ tem exatamente a mesma lógica, mas permite analisar o crescimento do número de operações relativamente a uma função quadrática.

---

## 12. Referências

- Slides do bloco 2 (semana 2) — disponíveis no Moodle;
- Exercícios de aula da semana 2 — disponíveis no Moodle;
- Documentação Java: <https://docs.oracle.com/en/java/>.
